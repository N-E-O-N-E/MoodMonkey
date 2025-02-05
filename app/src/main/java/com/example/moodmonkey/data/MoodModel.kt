package com.example.moodmonkey.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moodmonkey.R
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


val basicActivities: List<ActivityModel> = listOf(
    ActivityModel(activityName = "Sport", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(activityName = "Business", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(activityName = "Family", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(activityName = "Health", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(activityName = "Hobby", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(activityName = "Education", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(activityName = "Travel", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(activityName = "Entertainment", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(activityName = "Spirituality", activityIcon = R.drawable.baseline_circle_24),
)