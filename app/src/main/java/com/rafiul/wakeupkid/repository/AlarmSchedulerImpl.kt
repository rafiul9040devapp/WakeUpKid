package com.rafiul.wakeupkid.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.rafiul.wakeupkid.model.AlarmItem
import com.rafiul.wakeupkid.service.AlarmReceiver
import java.time.ZoneId

class AlarmSchedulerImpl(private val context: Context) : AlarmScheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)
    override fun scheduleAlarm(item: AlarmItem) {

        val intent = Intent(context,AlarmReceiver::class.java).apply {
            putExtra("EXTRA_MESSAGES",item.message)
        }

        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            item.alarmTime.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000,
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }

    override fun cancelAlarm(item: AlarmItem) {
        alarmManager.cancel(
            PendingIntent.getBroadcast(
                context,
                item.hashCode(),
                Intent(context,AlarmReceiver::class.java),
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        )
    }
}