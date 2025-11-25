package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.EntryDao
import com.mayurdw.fibretracker.data.helpers.convertFoodEntityToEntryEntity
import com.mayurdw.fibretracker.model.entity.FoodEntity
import javax.inject.Inject

class AddEntryUseCase @Inject constructor(
    private val entryDao: EntryDao
) : IAddEntryUseCase {
    override suspend fun insertNewEntry(foodEntity: FoodEntity, fibreQuantity: Int) {
        entryDao.insertEntry(convertFoodEntityToEntryEntity(foodEntity, fibreQuantity))
    }
}
