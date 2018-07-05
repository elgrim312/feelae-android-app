package com.example.azerty.feelae.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

const val NOTIFICATION_ID = "notification-id"
const val NOTIFICATION = "notification"
class NotificationPublisher : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val notificationManager = context!!.getSystemService(Context.NOTIFICATION_SERVICE)  as NotificationManager

        val notification = intent!!.getParcelableExtra(NOTIFICATION) as Notification
        val  id = intent.getIntExtra(NOTIFICATION_ID, 0)

        notificationManager.notify(id, notification)
    }

}