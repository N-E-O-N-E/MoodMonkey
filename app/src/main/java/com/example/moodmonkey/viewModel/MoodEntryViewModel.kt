package com.example.moodmonkey.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmonkey.data.MoodDatabase
import com.example.moodmonkey.data.EntryModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MoodEntryViewModel(application: Application) : AndroidViewModel(application) {

    init {

       //Todo: Abfrage der vorhandenen Activities #QUERY

    }


    private val dao = MoodDatabase.getDatabase(application.applicationContext).moodDao()
    val allMoods: StateFlow<List<EntryModel>> = dao
        .getAllItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    fun update(mood: EntryModel) {
        viewModelScope.launch {
            dao.update(mood = mood)
        }
    }

    fun delete(mood: EntryModel) {
        viewModelScope.launch {
            dao.delete(mood = mood)
        }
    }

    fun insert(mood: EntryModel) {
        viewModelScope.launch {
            dao.insert(mood = mood)
        }
    }






}