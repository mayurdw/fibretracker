package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.entity.FoodEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteFoodUseCase @Inject constructor(
    private val appDao: AppDao,
    private val dispatcher: CoroutineDispatcher
) : IDeleteFoodUseCase {
    override suspend fun deleteFood(foodEntity: FoodEntity) {
        withContext(dispatcher) {
            appDao.deleteFood(foodEntity)
        }
    }
}
