package com.example.moodmonkey.views.Components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun activitySlider(): Float {
    var moodSliderValue by remember { mutableFloatStateOf(50.0f) }

    Text(
        "Balance: ${"%.0f".format(moodSliderValue)}",
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(all = 10.dp)
            .border(1.dp, MaterialTheme.colorScheme.primary),
        textAlign = TextAlign.Center,
        fontSize = MaterialTheme.typography.titleLarge.fontSize
    )

    Slider(
        value = moodSliderValue,
        onValueChange = { moodSliderValue = it },
        valueRange = 1.0f..100.0f,
        modifier = Modifier.padding(horizontal = 25.dp),

    )
    return moodSliderValue
}