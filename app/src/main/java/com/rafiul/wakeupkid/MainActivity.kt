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
import com.rafiul.wakeupkid.model.AlarmItem
import com.rafiul.wakeupkid.repository.AlarmSchedulerImpl
import com.rafiul.wakeupkid.ui.theme.WakeUpKidTheme
import java.time.LocalDateTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduler = AlarmSchedulerImpl(this)
        var alarmItem: AlarmItem? = null
        setContent {
            WakeUpKidTheme {

                var secondText by remember {
                    mutableStateOf("")
                }

                var message by remember {
                    mutableStateOf("")
                }

                Surface(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .padding(32.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        OutlinedTextField(
                            value = secondText,
                            onValueChange = { secondText = it },
                            modifier = Modifier.fillMaxWidth(),
                            placeholder = {
                                Text(text = "Trigger Alarm in seconds")
                            },
                        )

                        Spacer(modifier = Modifier.height(16.dp))

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

                            Button(onClick = {
                                alarmItem = AlarmItem(
                                    alarmTime = LocalDateTime.now()
                                        .plusSeconds(secondText.toLong()),
                                    message = message
                                )
                                alarmItem?.let(scheduler::scheduleAlarm)
                                secondText= ""
                                message = ""
                            }) {
                                Text(text = "Schedule")
                            }

                            Button(onClick = {
                                alarmItem?.let(scheduler::cancelAlarm)
                            }) {
                                Text(text = "Cancel")
                            }

                        }

                    }
                }
            }
        }
    }
}

