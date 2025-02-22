package com.example.moodmonkey.views

import MoodEntryCardView
import android.R.attr.bottom
import android.R.attr.contentDescription
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodmonkey.R
import com.example.moodmonkey.data.EntryModel
import com.example.moodmonkey.data.basicActivities
import com.example.moodmonkey.viewModel.MoodEntryViewModel
import kotlinx.coroutines.flow.forEach


@Composable
fun MoodEntryListView(
    modifier: Modifier = Modifier,
    entries: List<EntryModel>,
    viewModel: MoodEntryViewModel
) {

    Box {
        Image(
            painter = if(!isSystemInDarkTheme()) { painterResource(id = R.drawable.wallpaper) } else { painterResource(id = R.drawable.wallpaper_darkmode)},
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.6F,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "MoodMonkey",
                fontWeight = FontWeight.Bold,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Hier war nicht die Datenbankabfrage drin
//                items(entries) { entry ->
//                    MoodEntryCardView(entry = entry, viewModel = viewModel)
//                }

                //Jetzt mit Datenbankabfrage
                viewModel.allMoods.value.forEach{ entry ->
                    item {
                        MoodEntryCardView(entry = entry, viewModel = viewModel)
                    }
                }
            }
        }
    }
}