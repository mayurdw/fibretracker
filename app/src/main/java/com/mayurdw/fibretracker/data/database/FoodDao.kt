package com.mayurdw.fibretracker.data.database

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

    @Query("SELECT * FROM food WHERE id LIKE :id")
    suspend fun getFoodById(id: Int): FoodEntity
}
