package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.data.helpers.convertFoodEntityToEntryEntity
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import javax.inject.Inject

class AddEntryUseCase @Inject constructor(
    private val entryDao: AppDao
) : IAddEntryUseCase {
    override suspend fun insertNewEntry(foodEntity: FoodEntity) {
        entryDao.upsertEntry(convertFoodEntityToEntryEntity(foodEntity))
    }

    override suspend fun insertNewEntry(foodEntryEntity: FoodEntryEntity) {
        entryDao.upsertEntry(foodEntryEntity)
    }
}
