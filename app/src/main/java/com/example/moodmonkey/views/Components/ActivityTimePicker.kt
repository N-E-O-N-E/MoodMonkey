package com.example.moodmonkey.views.Components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun activityTimePicker(modifier: Modifier = Modifier): String {
    var timePickerValue by remember { mutableStateOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm"))) }
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        timepicker { time ->
            timePickerValue = time.toString()
        }
    }

    Surface(modifier = Modifier.padding(end = 10.dp), shape = RoundedCornerShape(10.dp), color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)) {
        Text(
            timePickerValue, modifier = Modifier
                .width(200.dp)
                .height(40.dp)
                .padding(all = 5.dp)
                .clickable(onClick = { dialogState.show() }),
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.titleLarge.fontSize
        )
    }

    return timePickerValue
}