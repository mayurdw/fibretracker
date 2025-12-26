package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.entity.FoodEntity

interface IAddFoodUseCase {
    suspend fun upsertNewFood(newFoodItem: FoodEntity)
}
