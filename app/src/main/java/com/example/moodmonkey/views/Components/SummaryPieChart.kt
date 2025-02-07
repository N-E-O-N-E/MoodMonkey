package com.example.moodmonkey.views.Components

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moodmonkey.ui.theme.unspecified_scheme
import com.example.moodmonkey.viewModel.MoodEntryViewModel
import ir.ehsannarmani.compose_charts.PieChart
import ir.ehsannarmani.compose_charts.models.Pie
import kotlin.ranges.contains


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SummaryPieChart(viewModel: MoodEntryViewModel) {
    val angryMoods = viewModel.allMoods.value.filter { it.moodEntryBar <= 20 }
    val sadMoods = viewModel.allMoods.value.filter { it.moodEntryBar in 20.1..40.0 }
    val neutralMoods = viewModel.allMoods.value.filter { it.moodEntryBar in 40.1..60.0 }
    val happyMoods = viewModel.allMoods.value.filter { it.moodEntryBar in 60.1..80.0 }
    val amazingMoods = viewModel.allMoods.value.filter { it.moodEntryBar >= 80 }

    val pieData = listOf(
        Pie(
            label = "Angry",
            data = angryMoods.size.toDouble(),
            color = Color(0xFFE01111).copy(alpha = 0.8f),
            selectedColor = Color(0xFFDE5C5C),
        ),
        Pie(
            label = "Sad",
            data = sadMoods.size.toDouble(),
            color = Color(0xFF6223CE).copy(alpha = 0.8f),
            selectedColor =  Color(0xFF7355E1),
        ),
        Pie(
            label = "Neutral",
            data = neutralMoods.size.toDouble(),
            color = Color(0xFF2047D5).copy(alpha = 0.8f),
            selectedColor = Color(0xFF4EA7E7),
        ),
        Pie(
            label = "Happy",
            data = happyMoods.size.toDouble(),
            color = Color(0xFF44C51E).copy(alpha = 0.8f),
            selectedColor = Color(0xFF57E548),
        ),
        Pie(
            label = "Amazing",
            data = amazingMoods.size.toDouble(),
            color = Color(0xFFD3C11F).copy(alpha = 0.8f),
            selectedColor = Color(0xFFE5D552),
        ),
    )


    var data by remember {
        mutableStateOf(pieData)
    }

    val floatSpec = spring<Float>(
        dampingRatio = .3f,
        stiffness = Spring.StiffnessLow
    )


        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            PieChart(
                modifier = Modifier
                    .size(200.dp),
                data = data,
                onPieClick = {
                    println("${it.label} Clicked")
                    val pieIndex = data.indexOf(it)
                    data =
                        data.mapIndexed { mapIndex, pie -> pie.copy(selected = pieIndex == mapIndex) }
                },
                selectedScale = 1.2f,
                spaceDegreeAnimEnterSpec = floatSpec,
                colorAnimEnterSpec = tween(300),
                scaleAnimEnterSpec = floatSpec,
                colorAnimExitSpec = tween(300),
                scaleAnimExitSpec = tween(300),
                spaceDegreeAnimExitSpec = tween(300),
                selectedPaddingDegree = 4f,
                style = Pie.Style.Stroke(),
                spaceDegree = 7f
            )
        }
}