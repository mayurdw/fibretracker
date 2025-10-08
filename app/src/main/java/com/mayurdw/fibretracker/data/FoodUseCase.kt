package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.domain.FoodItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodUseCase : IFoodUseCase {
    @Inject
    lateinit var dao: FoodDao


    override suspend fun getFoods(): Flow<List<FoodItem>> {
        TODO()
    }

    override suspend fun insertNewFood(newFoodItem: FoodItem) {
        TODO("Not yet implemented")
    }
}
