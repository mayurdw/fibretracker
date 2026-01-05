package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.entity.FoodEntity

interface IDeleteFoodUseCase {
    suspend fun deleteFood(foodEntity: FoodEntity)
}
