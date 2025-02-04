package com.example.moodmonkey.ui.navigation

import androidx.annotation.DrawableRes
import com.example.moodmonkey.R
import kotlinx.serialization.Serializable


enum class NavItem(
    val route: Any,
    val label: String,
    @DrawableRes
    val icon: Int,
) {
    First(
        route =  DashboardRoute,
        label =  "Dash",
        icon = R.drawable.baseline_dashboard_customize_24),
    Second(
        route = NewEntryRoute,
        label ="Add Mood",
        icon = R.drawable.baseline_add_circle_outline_24),
    Third(
        route =  StatisticsRoute,
        label =  "Statistics",
        icon = R.drawable.baseline_bar_chart_24),

}


@Serializable
object DashboardRoute

@Serializable
object NewEntryRoute

@Serializable
object StatisticsRoute

@Serializable
data class DetailRoute(
    val moodEntryTitle: String,
    val moodEntryContent: String,
    val moodEntryBar: Int,
    val moodEntryDate: String,
    val moodEntryActivity: String
)
