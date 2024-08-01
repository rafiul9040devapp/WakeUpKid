package com.rafiul.wakeupkid.repository

import com.rafiul.wakeupkid.model.AlarmItem

interface AlarmScheduler {
    fun scheduleAlarm(item: AlarmItem)
    fun cancelAlarm(item: AlarmItem)
}