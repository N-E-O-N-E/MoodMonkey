package com.example.moodmonkey.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.moodmonkey.R
import com.example.moodmonkey.viewModel.MoodEntryViewModel
import com.example.moodmonkey.views.Components.SummaryLineChart
import com.example.moodmonkey.views.Components.SummaryPieChart

@Composable
fun StatisticView(modifier: Modifier = Modifier, viewModel: MoodEntryViewModel) {
    Box {
        Image(
            painter = if(!isSystemInDarkTheme()) { painterResource(id = R.drawable.wallpaper) } else { painterResource(id = R.drawable.wallpaper_darkmode)},
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.4F,
            modifier = Modifier.fillMaxSize()
        )

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)) {

            Text(
                "Statistics",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 15.dp),
                fontWeight = FontWeight.Bold, textAlign = TextAlign.Start,
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            )

            SummaryLineChart(viewModel = viewModel)
            SummaryPieChart(viewModel = viewModel)

        }
    }
}


