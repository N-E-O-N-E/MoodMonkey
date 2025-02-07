import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodmonkey.R
import com.example.moodmonkey.data.EntryModel
import com.example.moodmonkey.data.basicActivities
import com.example.moodmonkey.ui.theme.MoodMonkeyTheme
import com.example.moodmonkey.viewModel.MoodEntryViewModel
import com.example.moodmonkey.views.Components.AlertDialogPopUp
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MoodEntryCardView(
    entry: EntryModel, viewModel: MoodEntryViewModel
) {
    var showContent by remember { mutableStateOf(false) }
    var openAlertDialog = remember { mutableStateOf(false) }

    val activitiesForEntries by viewModel.getActivitiesForEntry(entry.id)
        .collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        when {
                            entry.moodEntryBar <= 20.0 -> Color.Red.copy(alpha = 0.2F)
                            entry.moodEntryBar in 20.1..40.0 -> Color.Magenta.copy(alpha = 0.2F)
                            entry.moodEntryBar in 40.1..60.0 -> Color.Blue.copy(alpha = 0.2F)
                            entry.moodEntryBar in 60.1..80.0 -> Color.Green.copy(alpha = 0.2F)
                            else -> Color.Yellow.copy(alpha = 0.2F)
                        }
                    )
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Date: ${entry.moodEntryDate}",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(
                    modifier = Modifier
                        .width(8.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = when {
                            entry.moodEntryBar <= 20.0 -> R.drawable.angry
                            entry.moodEntryBar in 20.1..40.0 -> R.drawable.sad
                            entry.moodEntryBar in 40.1..60.0 -> R.drawable.neutral
                            entry.moodEntryBar in 60.1..80.0 -> R.drawable.happy
                            else -> R.drawable.amazing

                        }
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )

                Spacer(
                    modifier = Modifier
                        .width(12.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = when {
                                entry.moodEntryBar <= 20.0 -> "Angry"
                                entry.moodEntryBar in 20.1..40.0 -> "Sad"
                                entry.moodEntryBar in 40.1..60.0 -> "Neutral"
                                entry.moodEntryBar in 60.1..80.0 -> "Happy"
                                else -> "Amazing"

                            },
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Icon(
                                painter = painterResource(id = R.drawable.baseline_access_time_24),
                                contentDescription = null
                            )
                            Text(
                                text = entry.moodEntryTime,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                            )
                            IconButton(
                                onClick = { showContent = !showContent }
                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = if (showContent) R.drawable.baseline_keyboard_double_arrow_up_24 else R.drawable.baseline_keyboard_double_arrow_down_24
                                    ),
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )



                    Spacer(
                        modifier = Modifier
                            .height(8.dp)
                    )

                    AnimatedVisibility(
                        visible = showContent
                    ) {
                        Column {

                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {

                                items(activitiesForEntries) { activity ->
                                    val activity =
                                        basicActivities.find { it.id == activity.activityId }
                                    activity?.let {

                                        Image(
                                            painter = painterResource(
                                                if (isSystemInDarkTheme()) {
                                                    it.activityIconLight
                                                } else {
                                                    it.activityIconDark
                                                }
                                            ),
                                            contentDescription = "",
                                            contentScale = ContentScale.Fit,
                                            modifier = Modifier
                                                .padding(horizontal = 2.dp)
                                                .width(28.dp)
                                                .height(28.dp)
                                        )
                                    }
                                }
                            }

                            Spacer(
                                modifier = Modifier
                                    .height(8.dp)
                            )


                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                thickness = 1.dp,
                                color = Color.Black
                            )

                            Text(
                                text = entry.moodEntryTitle,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                fontWeight = FontWeight.Bold,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(top = 8.dp)
                            )

                            Text(
                                text = entry.moodEntryContent,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                maxLines = 3,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(top = 8.dp)
                            )

                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                thickness = 1.dp,
                                color = Color.Black
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth(1f),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_delete_24),
                                    contentDescription = "Trash it",
                                    tint = Color.Red.copy(alpha = 0.65f),
                                    modifier = Modifier
                                        .padding(top = 8.dp)
                                        .clickable(onClick = {
                                            openAlertDialog.value = true
                                        })
                                )
                            }
                        }
                    }
                }
            }

            when {
                openAlertDialog.value -> {
                    AlertDialogPopUp(
                        onDismissRequest = { openAlertDialog.value = false },
                        onConfirmation = {
                            viewModel.viewModelScope.launch {
                                viewModel.delete(entry)
                                openAlertDialog.value = false
                            }
                        },
                        dialogTitle = "DELETE ENTRY ?",
                        dialogText = "Do you really want to delete the Entry with all Activities of them?",
                        icon = Icons.Default.Info
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoodEntryCardPreview() {
    MoodMonkeyTheme {
        MoodEntryCardView(
            entry = EntryModel(
                id = 1,
                moodEntryTitle = "Happy Day!",
                moodEntryContent = "Heute war ein toller Tag, weil ich nach langem endlich Milan wieder beleidigen konnte während Dieter am reden war..",
                moodEntryBar = 61f,
                moodEntryDate = "03.02.2025",
                moodEntryTime = "15:52"
            ), viewModel = viewModel()
        )
    }
}
