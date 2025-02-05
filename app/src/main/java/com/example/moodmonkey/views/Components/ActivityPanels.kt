package com.example.moodmonkey.views.Components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moodmonkey.data.ActivityModel
import com.example.moodmonkey.data.basicActivities

@Composable
fun ActivityCards(activityList: List<ActivityModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(
            horizontal = 10.dp,
            vertical = 10.dp
        ),
    ) {
        items(basicActivities) { activity ->
            Button(onClick = {

            }, modifier = Modifier.padding(horizontal = 5.dp)) {
                Text(
                    text = activity.activityName,
                    modifier = Modifier
                        .width(80.dp)
                        .height(20.dp)

                )
            }
        }
    }
}