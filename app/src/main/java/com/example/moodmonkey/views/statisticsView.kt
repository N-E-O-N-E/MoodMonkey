package com.example.moodmonkey.views

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.extensions.format
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.LineProperties
import ir.ehsannarmani.compose_charts.models.PopupProperties
import ir.ehsannarmani.compose_charts.models.StrokeStyle

@Composable
fun StatisticView(modifier: Modifier = Modifier) {
    val gridProperties = GridProperties(
        xAxisProperties = GridProperties.AxisProperties(
            thickness = .2.dp,
            color = SolidColor(Color.Gray.copy(alpha = .5f)),
            style = StrokeStyle.Dashed(intervals = floatArrayOf(15f,15f), phase = 10f),
        ),
        yAxisProperties = GridProperties.AxisProperties(
            thickness = .2.dp,
            color = SolidColor(Color.Gray.copy(alpha = .5f)),
            style = StrokeStyle.Dashed(intervals = floatArrayOf(15f,15f), phase = 10f),
        ),
    )
    val dividerProperties = DividerProperties(
        xAxisProperties = LineProperties(
            thickness = .2.dp,
            color = SolidColor(Color.Gray.copy(alpha = .5f)),
            style = StrokeStyle.Dashed(intervals = floatArrayOf(15f,15f), phase = 10f),
        ),
        yAxisProperties = LineProperties(
            thickness = .2.dp,
            color = SolidColor(Color.Gray.copy(alpha = .5f)),
            style = StrokeStyle.Dashed(intervals = floatArrayOf(15f,15f), phase = 10f),
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Headline
        Text(
            "Statistics",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 15.dp),
            fontWeight = FontWeight.Bold, textAlign = TextAlign.Start,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
        )


        val data = remember {
            listOf(
                Line(
                    label = "Windows",
                    values = listOf(
                        75.0,
                        5.0,
                        70.0,
                        85.0,
                        0.0
                    ),
                    color = SolidColor(Color(0xFF2B8130)),
                    firstGradientFillColor = Color(0xFF66BB6A).copy(alpha = .4f),
                    secondGradientFillColor = Color.Transparent,
                    strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                    gradientAnimationDelay = 1000,
                    drawStyle = DrawStyle.Stroke(.5.dp),
                    curvedEdges = true
                ),
                Line(
                    label = "Linux",
                    values = listOf(
                        1.0,
                        19.0,
                        22.0,
                        0.0,
                        5.0
                    ),
                    color = SolidColor(Color(0xFFDA860C)),
                    firstGradientFillColor = Color(0xFFFFA726).copy(alpha = .4f),
                    secondGradientFillColor = Color.Transparent,
                    strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                    gradientAnimationDelay = 1000,
                    drawStyle = DrawStyle.Stroke(.5.dp)
                ),
                Line(
                    label = "MacOS",
                    values = listOf(
                        4.0,
                        40.0,
                        58.0,
                        38.0,
                        22.0
                    ),
                    color = SolidColor(Color(0xFF0F73C4)),
                    firstGradientFillColor = Color(0xFF42A5F5).copy(alpha = .4f),
                    secondGradientFillColor = Color.Transparent,
                    strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                    gradientAnimationDelay = 1000,
                    drawStyle = DrawStyle.Stroke(.5.dp),
                    curvedEdges = true,
                ),
            )
        }
        Card(
            modifier = Modifier
                .height(270.dp)
                .fillMaxWidth()
                .weight(1f)
                .border(2.dp, Color.Transparent, RoundedCornerShape(12.dp)),
            elevation = CardDefaults.elevatedCardElevation(2.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xff2D2D2D)
            )
        ) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp)) {
                LineChart(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 22.dp),
                    data = data,
                    animationMode = AnimationMode.Together(delayBuilder = {
                        it * 500L
                    }),
                    gridProperties = gridProperties,
                    dividerProperties = dividerProperties,
                    popupProperties = PopupProperties(
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            color = Color.White,
                        ),
                        contentBuilder = {
                            it.format(1) + " Million"
                        },
                        containerColor = Color(0xff414141)
                    ),
                    indicatorProperties = HorizontalIndicatorProperties(
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            color = Color.White,
                        ),
                        contentBuilder = {
                            it.format(1) + " M"
                        },
                    ),
                    labelHelperProperties = LabelHelperProperties(
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    ),
                    curvedEdges = false
                )
            }
        }
    }
}