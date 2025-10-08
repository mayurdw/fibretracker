package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.domain.FoodItem
import kotlinx.coroutines.flow.Flow

interface IFoodUseCase {
    suspend fun getFoods(): Flow<List<FoodItem>>

    suspend fun insertNewFood(newFoodItem: FoodItem)
}
