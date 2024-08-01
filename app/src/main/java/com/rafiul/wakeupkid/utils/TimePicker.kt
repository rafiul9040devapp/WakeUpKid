package com.rafiul.wakeupkid.utils

import android.app.TimePickerDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun TimePicker(onTimeSelected: (Int, Int) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    val timePickerDialog = TimePickerDialog(
        context,
        { _, selectedHour, selectedMinute ->
            onTimeSelected(selectedHour, selectedMinute)
        },
        hour,
        minute,
        false
    )

    Button(onClick = { timePickerDialog.show() }) {
        Text("Pick Time")
    }
}

fun simpleTimeFormatPair(
    hour: Int,
    minute: Int
): Pair<Calendar, SimpleDateFormat> {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
    }
    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return Pair(calendar, format)
}
