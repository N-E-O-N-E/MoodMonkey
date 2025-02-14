package com.example.moodmonkey.viewModel

import android.app.Application
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.moodmonkey.data.ActivityModel
import com.example.moodmonkey.data.EntryModel
import com.example.moodmonkey.data.EntryToActivity
import com.example.moodmonkey.data.MoodDatabase
import com.example.moodmonkey.data.basicActivities
import com.example.moodmonkey.dataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

private val DATASTORE_ACTIVITY_STATE = booleanPreferencesKey("isActivityInitialized")

class MoodEntryViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = MoodDatabase.getDatabase(application.applicationContext).moodDao()
    private val dataStore = application.dataStore

    val savedValue: MutableStateFlow<Boolean> = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            val allDataFromDataStore: Preferences = dataStore.data.first()
            val isActivitySaved = allDataFromDataStore[DATASTORE_ACTIVITY_STATE] ?: false
            savedValue.value = isActivitySaved

            if (!savedValue.value) {
                insertActivity(basicActivities)
            }
        }
    }

    val allMoods: StateFlow<List<EntryModel>> = dao
        .getAllItems()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList()
        )

    val getLastEntry: StateFlow<List<EntryModel>> = dao
        .getMoodListLastEntryID()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList<EntryModel>()
        )

    val getActivitiesForEntry: (Int) -> StateFlow<List<EntryToActivity>> = { entryId ->
        dao
            .getActivitiesForEntry(entryId)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(),
                initialValue = emptyList()
            )
    }

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

    suspend fun insertActivity(activities: List<ActivityModel>) {
        activities.forEach { activity ->
            viewModelScope.launch {
                dao.initialActivitiesInsert(activity)
            }
        }
        savedValue.value = true
        saveActivityState()
    }

    private suspend fun saveActivityState() {
        dataStore.edit { value ->
            value[DATASTORE_ACTIVITY_STATE] = savedValue.value
        }
    }

    fun saveRelationchips(moodEntry: Int, activity: Int) {
        viewModelScope.launch {
            dao.insertRelationchip(EntryToActivity(entryId = moodEntry, activityId = activity))
        }
    }
}