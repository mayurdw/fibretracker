package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.data.helpers.convertFoodEntityToEntryEntity
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddEntryUseCase @Inject constructor(
    private val entryDao: AppDao,
    private val dispatcher: CoroutineDispatcher
) : IAddEntryUseCase {
    override suspend fun insertNewEntry(foodEntity: FoodEntity) {
        withContext(dispatcher) {
            entryDao.upsertEntry(convertFoodEntityToEntryEntity(foodEntity))
        }
    }

    override suspend fun insertNewEntry(foodEntryEntity: FoodEntryEntity) {
        withContext(dispatcher) {
            entryDao.upsertEntry(foodEntryEntity)
        }
    }
}
