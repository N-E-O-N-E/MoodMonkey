package com.example.moodmonkey

import android.R.attr.entries
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moodmonkey.navigation.DashboardRoute
import com.example.moodmonkey.navigation.NavItem
import com.example.moodmonkey.navigation.NewEntryRoute
import com.example.moodmonkey.navigation.StatisticsRoute
import com.example.moodmonkey.ui.theme.MoodMonkeyTheme
import com.example.moodmonkey.viewModel.MoodEntryViewModel
import com.example.moodmonkey.views.MoodEntryListView
import com.example.moodmonkey.views.NewEntry
import com.example.moodmonkey.views.StatisticView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MoodEntryViewModel = viewModel()
            val moodList by viewModel.allMoods.collectAsState()
            var selectedNavItems by remember { mutableStateOf(NavItem.First) }
            val navController: NavHostController = rememberNavController()

            MoodMonkeyTheme {
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
                        modifier = Modifier,

                        ) {

                        composable<DashboardRoute> {
                            MoodEntryListView(
                                modifier = Modifier.padding(innerPadding),
                                entries = moodList
                            )
                        }

                        composable<NewEntryRoute> {
                            NewEntry()
                        }

                        composable<StatisticsRoute> {
                            StatisticView()
                        }
                    }
                }
            }
        }
    }
}

