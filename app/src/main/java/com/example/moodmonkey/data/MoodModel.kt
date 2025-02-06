package com.example.moodmonkey.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.moodmonkey.R


@Entity(tableName = "moodList")
data class EntryModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val moodEntryTitle: String,
    val moodEntryContent: String,
    val moodEntryBar: Float,
    val moodEntryDate: String,
    val moodEntryTime: String,
)

@Entity(
    tableName = "entryToActivityList",
    foreignKeys = [
        ForeignKey(
            entity = EntryModel::class,
            parentColumns = ["id"],
            childColumns = ["entryId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ActivityModel::class,
            parentColumns = ["id"],
            childColumns = ["activityId"],
            onDelete = ForeignKey.CASCADE
        )
    ], indices = [Index(value = ["entryId"]), Index(value = ["activityId"])]
)
data class EntryToActivity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val entryId: Int,
    val activityId: Int
)


@Entity(tableName = "activityList")
data class ActivityModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val activityName: String,
    val activityIcon: Int,
)


val basicActivities: List<ActivityModel> = listOf(
    ActivityModel(id = 0, activityName = "Sport", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(id = 1, activityName = "Business", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(id = 2, activityName = "Family", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(id = 3, activityName = "Health", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(id = 4, activityName = "Hobby", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(id = 5, activityName = "Education", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(id = 6, activityName = "Travel", activityIcon = R.drawable.baseline_circle_24),
    ActivityModel(id = 7,activityName = "Entertainment", activityIcon = R.drawable.baseline_circle_24),
)