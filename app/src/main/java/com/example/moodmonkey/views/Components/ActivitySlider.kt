package com.example.moodmonkey.views.Components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun activitySlider(): Float {
    var moodSliderValue by remember { mutableFloatStateOf(0.5f) }

    Slider(
        value = moodSliderValue,
        onValueChange = { moodSliderValue = it },
        valueRange = 0.0f..1.0f,
        modifier = Modifier.padding(16.dp),

    )
    return moodSliderValue
}