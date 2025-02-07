package com.example.moodmonkey

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.moodmonkey.navigation.AppNavigation
import com.example.moodmonkey.ui.theme.MoodMonkeyTheme

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "isActivityInitialized")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoodMonkeyTheme(darkTheme = isSystemInDarkTheme(), dynamicColor = true) {
                AppNavigation()
            }
        }
    }
}
