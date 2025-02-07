package com.example.moodmonkey.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodmonkey.viewModel.MoodEntryViewModel
import com.example.moodmonkey.views.MoodEntryListView
import com.example.moodmonkey.views.NewEntryView
import com.example.moodmonkey.views.StatisticView

@Composable
fun AppNavigation() {
    val viewModel: MoodEntryViewModel = viewModel()
    val moodList by viewModel.allMoods.collectAsState()
    var selectedNavItems by remember { mutableStateOf(NavItem.First) }
    val navController: NavHostController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                NavItem.entries.forEach { item ->
                    NavigationBarItem(
                        selected = selectedNavItems == item,
                        onClick = {
                            selectedNavItems = item
                            navController.popBackStack()
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        },
                        icon = {
                            Icon(painter = painterResource(id = item.icon), item.label)
                        },
                        label = { Text(item.label) })
                }
            }
        }
    ) { innerPadding ->

        NavHost(
            startDestination = DashboardRoute,
            navController = navController,
            modifier = Modifier.padding(innerPadding),

            ) {

            composable<DashboardRoute> {
                MoodEntryListView(entries = moodList, viewModel = viewModel)
            }

            composable<NewEntryRoute> {
                NewEntryView(viewModel, navController)
            }

            composable<StatisticsRoute> {
                StatisticView(viewModel = viewModel)
            }
        }
    }

}