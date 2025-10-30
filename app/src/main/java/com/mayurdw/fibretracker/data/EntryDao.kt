package com.mayurdw.fibretracker.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry WHERE dateTime LIKE :currentDate")
    fun getEntries(currentDate: Long): List<FoodEntryEntity>

    @Query("SELECT * FROM entry WHERE dateTime BETWEEN :startTime AND :endTime ORDER BY dateTime DESC")
    fun getEntries(startTime: Long, endTime: Long): Flow<List<FoodEntryEntity>>

    @Query("SELECT * FROM entry")
    fun getAll(): List<FoodEntryEntity>

    @Query("SELECT * FROM entry")
    fun getAllFlow(): Flow<List<FoodEntryEntity>>

    @Upsert
    fun insertEntry(entryEntity: FoodEntryEntity)
}
