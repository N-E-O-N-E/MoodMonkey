package com.example.moodmonkey.views.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Composable
fun activityDatePicker(modifier: Modifier = Modifier): String {
    var datePickerValue by remember { mutableStateOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))) }
    val dialogState = rememberMaterialDialogState()

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            datePickerValue = date.toString().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        }
    }

    Text(
        datePickerValue, modifier = Modifier
            .width(220.dp)
            .height(50.dp)
            .padding(all = 10.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary)
            .clickable(onClick = { dialogState.show() }),
        textAlign = TextAlign.Center,
        fontSize = MaterialTheme.typography.titleLarge.fontSize
    )
    return datePickerValue
}