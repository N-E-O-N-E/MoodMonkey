package com.example.moodmonkey.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "moodList")
data class MoodEntryModel(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val moodEntryTitle: String,
    val moodEntryContent: String,
    val moodEntryBar: Float,
    val moodEntryDate: String,
    val moodEntryActivity: String

)

