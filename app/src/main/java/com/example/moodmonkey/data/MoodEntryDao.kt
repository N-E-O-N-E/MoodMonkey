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
    suspend fun insertRelationchip(relationchip: EntryToActivity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun initialActivitiesInsert(mood: ActivityModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mood: EntryModel)

    @Query("SELECT * FROM entryToActivityList WHERE entryId = :entryId")
    fun getActivitiesForEntry(entryId: Int): Flow<List<EntryToActivity>>

    @Query("SELECT * FROM moodList ORDER BY moodEntryDate, moodEntryTime DESC")
    fun getAllItems(): Flow<List<EntryModel>>

    @Query("SELECT * FROM moodList ORDER BY id DESC LIMIT 1")
    fun getMoodListLastEntryID(): Flow<List<EntryModel>>

    @Delete
    suspend fun delete(mood: EntryModel)

    @Update
    suspend fun update(mood: EntryModel)
}
