package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.domain.FoodItem

interface IFoodUseCase {
    suspend fun getFoods(): List<FoodItem>

    suspend fun insertNewFood(newFoodItem: FoodItem)
}
