package com.rafiul.wakeupkid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapp.components.DatePicker
import com.example.myapp.components.simpleDateFormatPair
import com.rafiul.wakeupkid.model.AlarmItem
import com.rafiul.wakeupkid.repository.AlarmSchedulerImpl
import com.rafiul.wakeupkid.ui.theme.WakeUpKidTheme
import com.rafiul.wakeupkid.utils.TimePicker
import com.rafiul.wakeupkid.utils.simpleTimeFormatPair
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AlarmSchedulerImpl(this)
        setContent {
            WakeUpKidTheme {
                var alarmItem by remember { mutableStateOf<AlarmItem?>(null) }
                var message by remember { mutableStateOf("") }
                var selectedDate by remember { mutableStateOf<Long?>(null) }
                var selectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }
                var displayDate by remember { mutableStateOf("") }
                var displayTime by remember { mutableStateOf("") }
                var visibility by remember { mutableStateOf(true) }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {

                        if (selectedDate != null && selectedTime != null && visibility){
                            Text(text = "Do you want to set Alarm: \n $displayDate At $displayTime")
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        OutlinedTextField(
                            value = message,
                            onValueChange = { message = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Alarm Message")
                            },
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            DatePicker { date ->
                                selectedDate = date
                                val (calendar, format) = simpleDateFormatPair(date)
                                displayDate = format.format(calendar.time)
                            }

                            TimePicker { hour, minute ->
                                selectedTime = Pair(hour, minute)
                                val (calendar, format) = simpleTimeFormatPair(hour, minute)
                                displayTime = format.format(calendar.time)
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Button(onClick = {
                                selectedDate?.let { date ->
                                    selectedTime?.let { (hour, minute) ->
                                        val calendar = Calendar.getInstance().apply {
                                            timeInMillis = date
                                            set(Calendar.HOUR_OF_DAY, hour)
                                            set(Calendar.MINUTE, minute)
                                            set(Calendar.SECOND, 0)
                                        }
                                        alarmItem = AlarmItem(
                                            alarmTime = calendar.timeInMillis,
                                            message = message
                                        )
                                    }
                                }
                                alarmItem?.let(scheduler::scheduleAlarm)
                                message = ""
                                visibility = false
                                selectedDate = null
                                selectedTime =null
                            },
                                enabled = selectedDate != null && selectedTime != null) {
                                Text(text = "Schedule")
                            }

                            Button(onClick = { alarmItem?.let(scheduler::cancelAlarm) },
                                enabled = selectedDate != null && selectedTime != null) {
                                Text(text = "Cancel")
                            }

                        }

                    }
                }
            }
        }
    }

    private
}

