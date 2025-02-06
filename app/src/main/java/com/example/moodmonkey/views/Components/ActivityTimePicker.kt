package com.example.moodmonkey.views.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalTime
import java.time.format.DateTimeFormatter


@Composable
fun activityTimePicker(modifier: Modifier = Modifier): String {
    var timePickerValue by remember { mutableStateOf("Time select") }
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker { time ->
            timePickerValue = time.toString().format(DateTimeFormatter.ofPattern("HH:MM"))
        }
    }

    Text(
        timePickerValue, modifier = Modifier
            .width(200.dp)
            .height(50.dp)
            .padding(all = 10.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .clickable(onClick = { dialogState.show() }),
        textAlign = TextAlign.Center,
        fontSize = MaterialTheme.typography.titleLarge.fontSize
    )

    return timePickerValue
}