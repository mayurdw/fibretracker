package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.entity.FoodEntity

interface IGetFoodUseCase {
    suspend fun getFoods(): List<FoodEntity>
    suspend fun getFoodById(id: Int): FoodEntity?
}
