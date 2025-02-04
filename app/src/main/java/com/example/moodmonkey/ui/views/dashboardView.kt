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
import com.example.moodmonkey.ui.data.moodEntryModel
import com.example.moodmonkey.ui.theme.MoodMonkeyTheme

@Composable
fun MoodEntryListView(
    entries: List<moodEntryModel>,
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
@Preview(showBackground = true)
@Composable
fun MoodEntryListPreview() {
    val sampleEntries = listOf(
        moodEntryModel(
            id = 1,
            moodEntryTitle = "Happy Day!",
            moodEntryContent = "Heute war ein toller Tag, weil ich nach langem endlich Milan wieder beleidigen konnte während Dieter am reden war..",
            moodEntryBar = 5,
            moodEntryDate = "03.02.2025",
            moodEntryActivity = "Spazieren"
        ),
        moodEntryModel(
            id = 2,
            moodEntryTitle = "Entspannter Abend",
            moodEntryContent = "Heute war ein toller Tag, weil ich nach langem endlich Milan wieder beleidigen konnte während Dieter am reden war..",
            moodEntryBar = 4,
            moodEntryDate = "02.02.2025",
            moodEntryActivity = "Lesen"
        ),
        moodEntryModel(
            id = 3,
            moodEntryTitle = "Workout geschafft!",
            moodEntryContent = "Heute war ein toller Tag, weil ich nach langem endlich Milan wieder beleidigen konnte während Dieter am reden war..",
            moodEntryBar = 5,
            moodEntryDate = "01.02.2025",
            moodEntryActivity = "Laufen"
        )
        ,
        moodEntryModel(
            id = 4,
            moodEntryTitle = "Entspannter Abend",
            moodEntryContent = "Heute war ein toller Tag, weil ich nach langem endlich Milan wieder beleidigen konnte während Dieter am reden war..",
            moodEntryBar = 4,
            moodEntryDate = "02.02.2025",
            moodEntryActivity = "Lesen"
        ),
        moodEntryModel(
            id = 5,
            moodEntryTitle = "Entspannter Abend",
            moodEntryContent = "Heute war ein toller Tag, weil ich nach langem endlich Milan wieder beleidigen konnte während Dieter am reden war..",
            moodEntryBar = 4,
            moodEntryDate = "02.02.2025",
            moodEntryActivity = "Lesen"
        ),
    )

    MoodMonkeyTheme {
        MoodEntryListView(
            entries = sampleEntries
        )
    }
}
