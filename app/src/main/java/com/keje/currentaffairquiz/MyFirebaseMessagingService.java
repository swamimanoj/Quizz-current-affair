package com.keje.currentaffairquiz;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String TAG = "MANOJ";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        showNotification( remoteMessage.getNotification().getBody() );
        // ...

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            showNotification( remoteMessage.getNotification().getBody() );

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.

            } else {
                // Handle message within 10 seconds

            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            showNotification( remoteMessage.getNotification().getBody() );
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }

    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

    }

   public void showNotification(String message){
        Intent intent = new Intent(this, MainActivity.class);


        PendingIntent pi = PendingIntent.getActivity( this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(
                        this, "222")
                        .setContentTitle("Start taking Quiz")
                        .setAutoCancel(true)
                        .setSmallIcon( R.drawable.ic_launcher_background )
                        //.setSound(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.electro))
                        .setContentText(message)
                        .setContentIntent(pi)
                ;

        NotificationManager notificationManager = (NotificationManager) getSystemService( NOTIFICATION_SERVICE );
        notificationManager.notify( 0, builder.build() );

    }
}
