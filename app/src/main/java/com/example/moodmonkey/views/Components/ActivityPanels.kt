package com.example.moodmonkey.views.Components

import android.R.attr.contentDescription
import android.R.attr.onClick
import android.R.attr.text
import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.moodmonkey.data.ActivityModel
import com.example.moodmonkey.data.basicActivities
import com.example.moodmonkey.R


@SuppressLint("MutableCollectionMutableState")
@Composable
fun activityCards(activityList: List<ActivityModel>): List<ActivityModel> {
    var selectedActivities by remember {
        mutableStateOf(emptyList<ActivityModel>())
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(vertical = 10.dp),
    ) {
        items(basicActivities) { activity ->
            val isSelected = activity in selectedActivities

            ElevatedButton(
                onClick = {
                    selectedActivities = if (isSelected) {
                        selectedActivities - activity
                    } else {
                        selectedActivities + activity
                    }
                },
                modifier = Modifier.padding(horizontal = 2.dp)
            ) {
                Row {

                    Icon(painter = painterResource(id = if(isSystemInDarkTheme()) { activity.activityIconLight } else { activity.activityIconDark }), contentDescription = "")
                    Text(
                        text = "\t${activity.activityName}",
                        modifier = Modifier
                            .height(20.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (isSelected) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_check_circle_24),
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
    return selectedActivities
}