import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moodmonkey.R
import com.example.moodmonkey.ui.data.MoodEntryModel
import com.example.moodmonkey.ui.theme.MoodMonkeyTheme

@Composable
fun MoodEntryCardView(
    entry: MoodEntryModel,
) {
    var showContent by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFF59D).copy(alpha = 0.35f))
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = entry.moodEntryDate,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier
                    .width(8.dp))
                Text(
                    text = "Today",
                    color = Color.Gray
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.kongtainer),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                )

                Spacer(modifier = Modifier
                    .width(12.dp))

                Column(modifier = Modifier
                    .weight(1f)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = entry.moodEntryTitle,
                            fontWeight = FontWeight.Bold
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "⏰ 12:30",
                            )
                            IconButton(
                                onClick = { showContent = !showContent }
                            ) {
                                Icon(
                                    painter = painterResource(
                                        id = if (showContent) R.drawable.baseline_arrow_drop_down_circle_24 else R.drawable.baseline_circle_24
                                    ),
                                    contentDescription = null
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier
                        .height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier
                        .height(8.dp))

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth(),
                        thickness = 1.dp,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier
                        .height(8.dp))

                    AnimatedVisibility(
                        visible = showContent
                    ) {
                        Text(
                            text = entry.moodEntryContent,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                    }
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
            entry = MoodEntryModel(
                id = 1,
                moodEntryTitle = "Happy Day!",
                moodEntryContent = "Heute war ein toller Tag, weil ich nach langem endlich Milan wieder beleidigen konnte während Dieter am reden war..",
                moodEntryBar = 0.5f,
                moodEntryDate = "03.02.2025",
                moodEntryActivity = "Spazieren"
            )
        )
    }
}
