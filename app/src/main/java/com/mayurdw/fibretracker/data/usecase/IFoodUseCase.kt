package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.entity.FoodEntity

interface IFoodUseCase {
    suspend fun getFoods(): List<FoodEntity>
    suspend fun getFoodById(id: Int): FoodEntity
    suspend fun insertNewFood(newFoodItem: FoodEntity)
}
