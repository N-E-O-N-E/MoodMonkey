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
        )
    ], indices = [Index(value = ["entryId"])]
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
    val activityIconLight: Int,
    val activityIconDark: Int
)


val basicActivities: List<ActivityModel> = listOf(
    ActivityModel(id = 0, activityName = "Sport", activityIconDark = R.drawable.baseline_sports_tennis_24, activityIconLight = R.drawable.baseline_sports_tennis_24_white),
    ActivityModel(id = 1, activityName = "Business", activityIconDark = R.drawable.baseline_business_center_24, activityIconLight = R.drawable.baseline_business_center_24_white),
    ActivityModel(id = 2, activityName = "Family", activityIconDark = R.drawable.baseline_family_restroom_24, activityIconLight = R.drawable.baseline_family_restroom_24_white),
    ActivityModel(id = 3, activityName = "Health", activityIconDark = R.drawable.baseline_healing_24, activityIconLight = R.drawable.baseline_healing_24_white),
    ActivityModel(id = 4, activityName = "Hobby", activityIconDark = R.drawable.baseline_directions_bike_24, activityIconLight = R.drawable.baseline_directions_bike_24_white),
    ActivityModel(id = 5, activityName = "Education", activityIconDark = R.drawable.baseline_menu_book_24, activityIconLight = R.drawable.baseline_menu_book_24_white),
    ActivityModel(id = 6, activityName = "Travel", activityIconDark = R.drawable.baseline_flight_takeoff_24, activityIconLight = R.drawable.baseline_flight_takeoff_24_white),
    ActivityModel(id = 7,activityName = "SocialMedia", activityIconDark = R.drawable.baseline_live_tv_24, activityIconLight = R.drawable.baseline_live_tv_24_white),
)