package com.mayurdw.fibretracker.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodEntryDao {
    @Query("SELECT * FROM entry WHERE date BETWEEN :startTime AND :endTime ORDER BY date DESC")
    fun getEntries(startTime: Long, endTime: Long): Flow<List<FoodEntryEntity>>

    @Query("SELECT * FROM entry")
    fun getAllEntries(): List<FoodEntryEntity>

    @Upsert
    fun insertEntry(entryEntity: FoodEntryEntity)

    @Query("SELECT * FROM food")
    fun getAllFoods(): List<FoodEntity>

    @Upsert
    suspend fun insertFood(foodEntity: FoodEntity)

    @Query("SELECT * FROM food WHERE id LIKE :id")
    suspend fun getFoodById(id: Int): FoodEntity
}
