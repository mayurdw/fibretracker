package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.entity.FoodEntity
import kotlinx.coroutines.flow.Flow

interface IGetFoodUseCase {
    suspend fun getFoods(): List<FoodEntity>
    suspend fun getFoodById(id: Int): Flow<Result<FoodEntity>>
}
