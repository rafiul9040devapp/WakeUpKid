package com.rafiul.wakeupkid.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.rafiul.wakeupkid.utils.NotificationHelper

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("EXTRA_MESSAGES") ?: return
        println("Alarm is Triggered: $message")
        if (context != null) {
            NotificationHelper.showNotification(context, "Alarm Manager", message)
        }
    }
}