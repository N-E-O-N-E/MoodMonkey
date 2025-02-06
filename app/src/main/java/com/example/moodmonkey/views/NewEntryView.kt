package com.example.moodmonkey.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.moodmonkey.R
import com.example.moodmonkey.data.ActivityModel
import com.example.moodmonkey.data.EntryModel
import com.example.moodmonkey.data.basicActivities
import com.example.moodmonkey.navigation.DashboardRoute
import com.example.moodmonkey.viewModel.MoodEntryViewModel
import com.example.moodmonkey.views.Components.AlertDialogPopUp
import com.example.moodmonkey.views.Components.activityCards
import com.example.moodmonkey.views.Components.activityDatePicker
import com.example.moodmonkey.views.Components.activitySlider
import com.example.moodmonkey.views.Components.activityTimePicker
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewEntryView(
    viewModel: MoodEntryViewModel,
    navController: NavHostController
) {
    val lastEntry by viewModel.getLastEntry.collectAsState()
    val openAlertDialog = remember { mutableStateOf(false) }

    var moodTitle by remember { mutableStateOf("") }
    var moodContent by remember { mutableStateOf("") }
    var datePickerValue: String = ""
    var timePickerValue: String = ""

    Box {
        Image(
            painter = painterResource(id = R.drawable.wallpaper),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.4F,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            // Headline
            Text(
                "New Mood",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Start,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            )
            // Component Slider
            var moodSliderValue = activitySlider()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp)
                    .padding(horizontal = 5.dp)
            ) {
                // Component DatePicker
                datePickerValue = activityDatePicker()
                // Component TimePicker
                timePickerValue = activityTimePicker()
            }
            // Component ActivityCard
            var selectedActivities: List<ActivityModel> =
                activityCards(activityList = basicActivities)


            TextField(value = moodTitle,
                onValueChange = { moodTitle = it },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(vertical = 5.dp),
                shape = RectangleShape,
                singleLine = false,
                placeholder = { Text("Title") }
            )

            TextField(value = moodContent,
                onValueChange = { moodContent = it },
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(150.dp)
                    .padding(vertical = 5.dp),
                shape = RectangleShape,
                singleLine = false,
                placeholder = { Text("Notice") }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = 10.dp),
                onClick = {
                    openAlertDialog.value = true
                }

            ) {
                Text("Save your Mood")
            }
            when {
                openAlertDialog.value -> {
                    AlertDialogPopUp(
                        onDismissRequest = { openAlertDialog.value = false },
                        onConfirmation = {
                            viewModel.viewModelScope.launch {
                                var newMoodEntry = EntryModel(
                                    moodEntryTitle = moodTitle,
                                    moodEntryContent = moodContent,
                                    moodEntryBar = moodSliderValue.toFloat(),
                                    moodEntryDate = datePickerValue,
                                    moodEntryTime = timePickerValue
                                )
                                viewModel.insert(newMoodEntry)

                                delay(600)

                                var lastEntryElement = lastEntry.lastOrNull()
                                selectedActivities.forEach { activity ->
                                    viewModel.saveRelationchips(
                                        lastEntryElement?.id ?: 0,
                                        activity.id
                                    )
                                }

                                // Reset Values
                                moodTitle = ""
                                moodContent = ""
                                moodSliderValue = 50.0f
                                datePickerValue = ""
                                timePickerValue = ""
                                selectedActivities = emptyList()

                                openAlertDialog.value = false
                                navController.navigate(DashboardRoute)
                            }
                        },
                        dialogTitle = "SAVE ENTRY?",
                        dialogText = "Do you really want to save?",
                        icon = Icons.Default.Info
                    )
                }
            }
        }
    }
}

