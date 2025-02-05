package com.example.moodmonkey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import com.example.moodmonkey.navigation.AppNavigation
import com.example.moodmonkey.ui.theme.MoodMonkeyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            // dis is main brah not gpt brah was machen wir nur mit diesem projekt
            MoodMonkeyTheme {
                AppNavigation()
            }
        }
    }
}

