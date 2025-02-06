package com.example.moodmonkey.views.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moodmonkey.R
import java.util.Map.entry
import kotlin.ranges.contains


@Composable
fun activitySlider(): Float {
    var moodSliderValue by remember { mutableFloatStateOf(50.0f) }

    Image(
        painter = painterResource(
            id = when {
                moodSliderValue <= 20.0 -> R.drawable.angry
                moodSliderValue in 20.1..40.0 -> R.drawable.sad
                moodSliderValue in 40.1..60.0 -> R.drawable.neutral
                moodSliderValue in 60.1..80.0 -> R.drawable.happy
                else -> R.drawable.amazing
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .size(60.dp)
            .clip(CircleShape)
    )

    Text(
        "Mood Level: ${"%.0f".format(moodSliderValue)}",
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .padding(all = 5.dp),
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