package com.example.myfirebaseapplication.Constants

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.myfirebaseapplication.MainActivity
import com.example.myfirebaseapplication.R
import com.example.myfirebaseapplication.Receiver.SnoozeReceiver
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private var FLAGS = 0
fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {


    // TODO: Step 1.11 create intent
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // TODO: Step 1.12 create PendingIntent
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

// TODO: Step 2.0 add style
    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.noti
    )
    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)


    // TODO: Step 2.2 add snooze action
    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent =
        PendingIntent.getBroadcast(applicationContext, REQUEST_CODE, snoozeIntent, FLAGS)

    // TODO: Step 1.2 get an instance of NotificationCompat.Builder
    // Build the notification
    val builder = NotificationCompat.Builder(
        applicationContext,
        // TODO: Step 1.8 use the new 'breakfast' notification channel
        applicationContext.getString(R.string.egg_notification_channel_id)
    )
        // TODO: Step 1.3 set title, text and icon to builder
        .setSmallIcon(R.drawable.noti)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        // TODO: Step 1.13 set content intent
        .setContentIntent(contentPendingIntent)
        // TODO: Step 2.1 add style to builder
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImage)
        // TODO: Step 2.3 add snooze action
        .addAction(
            R.drawable.noti,
            applicationContext.getString(R.string.snooze),
            snoozePendingIntent
        )
        // TODO: Step 2.5 set priority
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

    // TODO Step 1.4 call notify
    // Deliver the notification
    notify(NOTIFICATION_ID, builder.build())
}

// TODO: Step 1.14 Cancel all notifications
/**
 * Cancels all notifications.
 *
 */
fun NotificationManager.cancelNotifications() {
    cancelAll()
}