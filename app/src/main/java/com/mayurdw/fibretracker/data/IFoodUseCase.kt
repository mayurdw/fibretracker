package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.entity.FoodEntity

interface IFoodUseCase {
    suspend fun getFoods(): List<FoodEntity>

    suspend fun insertNewFood(newFoodItem: FoodEntity)
}
