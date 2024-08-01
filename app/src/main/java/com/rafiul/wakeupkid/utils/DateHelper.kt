package com.example.myapp.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DatePicker(onDateSelected: (Long) -> Unit) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            calendar.set(year, month, dayOfMonth)
            onDateSelected(calendar.timeInMillis)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Button(
        onClick = {
            datePickerDialog.show()
        },
    ) {
        Text(text = "Pick Date")
    }
}

fun simpleDateFormatPair(date: Long): Pair<Calendar, SimpleDateFormat> {
    val calendar = Calendar.getInstance().apply { timeInMillis = date }
    val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return Pair(calendar, format)
}
