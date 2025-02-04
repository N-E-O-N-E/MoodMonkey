package com.example.moodmonkey.ui.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmonkey.ui.data.MoodDatabase
import com.example.moodmonkey.ui.data.MoodEntryModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MoodEntryViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = MoodDatabase.getDatabase(application.applicationContext).moodDao()
    val allMoods: StateFlow<List<MoodEntryModel>> = dao
        .getAllItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun update(mood: MoodEntryModel) {
        viewModelScope.launch {
            dao.update(mood = mood)
        }
    }

    fun delete(mood: MoodEntryModel) {
        viewModelScope.launch {
            dao.delete(mood = mood)
        }
    }

    fun insert(mood: MoodEntryModel) {
        viewModelScope.launch {
            dao.insert(mood = mood)
        }
    }






}