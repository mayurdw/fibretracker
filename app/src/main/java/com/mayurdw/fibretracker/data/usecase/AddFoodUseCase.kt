package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.entity.FoodEntity
import javax.inject.Inject

class AddFoodUseCase @Inject constructor(
    private val dao: AppDao
) : IAddFoodUseCase {

    override suspend fun upsertNewFood(newFoodItem: FoodEntity) {
        dao.upsertNewFood(foodEntity = newFoodItem)
    }
}
