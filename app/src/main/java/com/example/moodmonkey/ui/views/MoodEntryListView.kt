package com.example.moodmonkey.ui.views

import MoodEntryCardView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodmonkey.ui.data.MoodEntryModel
import com.example.moodmonkey.ui.theme.MoodMonkeyTheme
import com.example.moodmonkey.ui.viewModel.MoodEntryViewModel

@Composable
fun MoodEntryListView(
    modifier: Modifier = Modifier,
    entries: List<MoodEntryModel>,
    ) {
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
                .fillMaxSize()
        ) {
            items(entries) { entry ->
                MoodEntryCardView(entry = entry)
            }
        }
    }
}

