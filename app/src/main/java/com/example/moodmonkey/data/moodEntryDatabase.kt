package com.example.moodmonkey.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [MoodEntryModel::class], version = 1, exportSchema = false)
abstract class MoodDatabase : RoomDatabase() {
    abstract fun moodDao(): MoodDao

    companion object {
        @Volatile
        private var Instance: MoodDatabase? = null

        fun getDatabase(context: Context): MoodDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MoodDatabase::class.java, "mood_database")
                    .build().also { Instance = it }
            }
        }
    }
}
