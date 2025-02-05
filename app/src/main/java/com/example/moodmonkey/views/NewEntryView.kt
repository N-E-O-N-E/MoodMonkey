package com.example.moodmonkey.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmonkey.data.EntryModel
import com.example.moodmonkey.data.basicActivities
import com.example.moodmonkey.views.Components.ActivityCards
import com.example.moodmonkey.views.Components.activitySlider


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEntryView() {
    var moodContent by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "New Mood Entry", modifier = Modifier.padding(all = 10.dp),
            fontSize = MaterialTheme.typography.headlineLarge.fontSize
        )


        // Components
        //ActivityDatePicker()
        var moodSliderValue = activitySlider()
        ActivityCards(activityList = basicActivities)


        //TODO: Content Multiline TextField

        TextField(value = moodContent,
            onValueChange = { moodContent = it },
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .padding(all = 10.dp),
            shape = RectangleShape,
            singleLine = false,
            placeholder = { Text("Add Content") }
        )


        //TODO: Save Button -> Creates new MoodEntry with Date

        Button(
            onClick = {
                val newMoodEntry = EntryModel(
                    moodEntryTitle = "",
                    moodEntryContent = moodContent,
                    moodEntryBar = moodSliderValue,
                    moodEntryDate = ""
                )

            }
        ) {
            Text("Save Entry")
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun PreviewNewEntry() {
    NewEntryView()
}