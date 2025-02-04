package com.example.moodmonkey.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface MoodDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mood: MoodEntryModel)

    @Query("SELECT * from moodList ORDER BY id ASC")
    fun getAllItems(): Flow<List<MoodEntryModel>>

    @Delete
    suspend fun delete(mood: MoodEntryModel)

    @Update
    suspend fun update(mood: MoodEntryModel)
}
