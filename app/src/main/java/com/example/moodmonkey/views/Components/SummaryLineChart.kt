package com.example.moodmonkey.views.Components

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moodmonkey.viewModel.MoodEntryViewModel
import ir.ehsannarmani.compose_charts.ColumnChart
import ir.ehsannarmani.compose_charts.extensions.format
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.BarProperties
import ir.ehsannarmani.compose_charts.models.Bars
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.IndicatorCount
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.PopupProperties
import kotlin.ranges.contains


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SummaryLineChart(viewModel: MoodEntryViewModel) {
    val columnGridProperties = GridProperties(
        enabled = true,
        xAxisProperties = GridProperties.AxisProperties(
            thickness = .2.dp,
            color = SolidColor(Color.Gray.copy(alpha = .6f))
        ),
        yAxisProperties = GridProperties.AxisProperties(
            thickness = .2.dp,
            color = SolidColor(Color.Gray.copy(alpha = .6f))
        ),
    )

    val angryMoods = viewModel.allMoods.value.filter { it.moodEntryBar <= 20 }
    val sadMoods = viewModel.allMoods.value.filter { it.moodEntryBar in 20.1..40.0 }
    val neutralMoods = viewModel.allMoods.value.filter { it.moodEntryBar in 40.1..60.0 }
    val happyMoods = viewModel.allMoods.value.filter { it.moodEntryBar in 60.1..80.0 }
    val amazingMoods = viewModel.allMoods.value.filter { it.moodEntryBar >= 80 }


    val data = remember {
        listOf(
            Bars(
                label = "Summary of Moods",
                values = listOf(
                    Bars.Data(
                        label = "Angry",
                        value = angryMoods.size.toDouble(),
                        color = Brush.verticalGradient(
                            listOf(
                                Color(0xFFE01111),
                                Color(0xFFDE5C5C),
                            )
                        )
                    ),
                    Bars.Data(
                        label = "Sad",
                        value = sadMoods.size.toDouble(),
                        color = Brush.verticalGradient(
                            listOf(
                                Color(0xFF6223CE),
                                Color(0xFF7355E1),
                            )
                        )
                    ),
                    Bars.Data(
                        label = "Neutral",
                        value = neutralMoods.size.toDouble(),
                        color = Brush.verticalGradient(
                            listOf(
                                Color(0xFF2047D5),
                                Color(0xFF4EA7E7),
                            )
                        )
                    ),
                    Bars.Data(
                        label = "Happy",
                        value = happyMoods.size.toDouble(),
                        color = Brush.verticalGradient(
                            listOf(
                                Color(0xFF44C51E),
                                Color(0xFF57E548),
                            )
                        )
                    ),
                    Bars.Data(
                        label = "Amazing",
                        value = amazingMoods.size.toDouble(),
                        color = Brush.verticalGradient(
                            listOf(
                                Color(0xFFD3C11F),
                                Color(0xFFE5D552),
                            )
                        )
                    ),
                )
            ),
        )
    }

    Column(
        modifier = Modifier.padding(bottom = 10.dp)

    ) {
        Card(
            modifier = Modifier
                .height(270.dp)
                .fillMaxWidth(1f)
                .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
            elevation = CardDefaults.elevatedCardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xff2D2D2D)
            )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 12.dp)
            ) {
                ColumnChart(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 22.dp),
                    data = data,
                    barProperties = BarProperties(
                        cornerRadius = Bars.Data.Radius.Rectangle(
                            topRight = 6.dp,
                            topLeft = 6.dp
                        ),
                        spacing = 30.dp,
                        thickness = 30.dp
                    ),
                    indicatorProperties = HorizontalIndicatorProperties(
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            color = Color.White
                        ),
                        count = IndicatorCount.CountBased(count = 4),
                    ),
                    gridProperties = columnGridProperties,
                    labelProperties = LabelProperties(
                        enabled = true,
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    ),
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    ),
                    animationMode = AnimationMode.Together(delayBuilder = { it * 100L }),
                    animationDelay = 300,
                    popupProperties = PopupProperties(
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            color = Color.White,
                        ),
                        contentBuilder = {
                            it.format(1) + " Million"
                        },
                        containerColor = Color(0xff414141),
                    ),
                    labelHelperProperties = LabelHelperProperties(
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    )
                )
            }
        }
    }

}