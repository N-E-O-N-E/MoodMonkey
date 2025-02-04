package com.example.moodmonkey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.moodmonkey.ui.theme.MoodMonkeyTheme
import com.example.moodmonkey.ui.viewModel.MoodEntryViewModel
import com.example.moodmonkey.ui.views.MoodEntryListView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MoodEntryViewModel = viewModel()
            val moodList by viewModel.allMoods.collectAsState()
            MoodMonkeyTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innerPadding ->

                    MoodEntryListView(
                        entries = moodList,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

