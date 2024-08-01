package com.rafiul.wakeupkid.utils

import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.window.DialogProperties
import java.time.LocalDate

@Composable
fun DatePickerDialog(
    onDismissRequest: () -> Unit,
    onDateChange: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    initialDate: LocalDate? = null,
    today: LocalDate = LocalDate.now(),
    showDaysAbbreviations: Boolean = true,
    highlightToday: Boolean = true,
    title: @Composable (() -> Unit)? = null,
    buttonColors: ButtonColors = ButtonDefaults.textButtonColors(),
    shape: Shape = AlertDialogDefaults.shape,
    containerColor: Color = AlertDialogDefaults.containerColor,
    titleContentColor: Color = AlertDialogDefaults.titleContentColor,
    tonalElevation: Dp = AlertDialogDefaults.TonalElevation,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
) {
}