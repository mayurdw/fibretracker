package com.mayurdw.fibretracker.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mayurdw.fibretracker.model.entity.Food

@Dao
interface FoodDao {
    @Query("SELECT * FROM food")
    fun getAll(): List<Food>

    @Upsert
    suspend fun insertFood(food: Food)
}
