package com.rafiul.wakeupkid.model

import java.time.LocalDateTime

data class AlarmItem(
    val alarmTime : LocalDateTime,
    val message : String
)
