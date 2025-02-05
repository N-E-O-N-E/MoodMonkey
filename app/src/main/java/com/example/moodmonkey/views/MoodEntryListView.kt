package com.example.moodmonkey.views

import MoodEntryCardView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.moodmonkey.data.EntryModel
import com.example.moodmonkey.data.basicActivities


@Composable
fun MoodEntryListView(modifier: Modifier = Modifier, entries: List<EntryModel>) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "MoodMonkey",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(entries) { entry ->
                MoodEntryCardView(entry = entry)
            }
        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(basicActivities) { activity ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    Icon(
                        painter = painterResource(id = activity.activityIcon),
                        contentDescription = "$activity.activityName"
                    )
                    Text(activity.activityName, modifier = Modifier.padding(all = 10.dp))
                }

            }
        }

    }
}