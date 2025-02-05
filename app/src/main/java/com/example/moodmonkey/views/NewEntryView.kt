package com.example.moodmonkey.views

import android.R.attr.fontStyle
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodmonkey.data.EntryModel
import com.example.moodmonkey.viewModel.MoodEntryViewModel


@Composable
fun NewEntryView(
    viewModel: MoodEntryViewModel = viewModel(),
    modifier: Modifier = Modifier
) {

    var moodSlider by remember { mutableStateOf(0.5f) }
    var moodContent by remember { mutableStateOf("") }

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {

        Text("New Entry", modifier = Modifier.padding(all = 10.dp))


        //TODO: Slider

        Slider(
            value = moodSlider,
            onValueChange = { moodSlider = it },
            valueRange = 0.0f..1.0f,
            modifier = Modifier.padding(16.dp)
        )

        //TODO: ActivityList LazyRow


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
                    moodEntryBar = moodSlider,
                    moodEntryDate = ""
                )
                viewModel.insert(newMoodEntry)
            }
        ) {
            Text("Save Entry")
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, name = "NewEntry")
@Composable
private fun NewEntryViewPreview() {
    NewEntryView()
}