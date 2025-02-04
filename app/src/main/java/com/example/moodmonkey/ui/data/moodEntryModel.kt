package com.example.moodmonkey.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "moodList")
data class moodEntryModel(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val moodEntryTitle: String,
    val moodEntryContent: String,
    val moodEntryBar: Int,
    val moodEntryDate: String,
    val moodEntryActivity: String

)

