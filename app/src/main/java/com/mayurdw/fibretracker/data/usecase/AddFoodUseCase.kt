package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.entity.FoodEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddFoodUseCase @Inject constructor(
    private val dao: AppDao,
    private val dispatcher: CoroutineDispatcher
) : IAddFoodUseCase {

    override suspend fun upsertNewFood(newFoodItem: FoodEntity) {
        withContext(dispatcher) {
            dao.upsertNewFood(foodEntity = newFoodItem)
        }
    }
}
