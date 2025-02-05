package com.example.moodmonkey.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID


@Entity(tableName = "moodList")
data class EntryModel(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val moodEntryTitle: String,
    val moodEntryContent: String,
    val moodEntryBar: Float,
    val moodEntryDate: String,
)

@Entity(tableName = "entryToActivityList")
data class entryToActivity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val entryId: Long,
    val activityId: Long?,
)

@Entity(tableName = "activityList")
data class ActivityModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val activityName: String,
    val activityIcon: Int,
)

/*
        INSERT INTO moodList VALUE(ID, E1)
        INSERT INTO moodList VALUE(ID, A1)

        INSERT INTO entryToActivity VALUE(ID, E1, A1)
*/