package com.mayurdw.fibretracker.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mayurdw.fibretracker.model.entity.FoodEntity

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAll(): List<FoodEntity>

    @Upsert
    suspend fun insertFood(foodEntity: FoodEntity)
}
