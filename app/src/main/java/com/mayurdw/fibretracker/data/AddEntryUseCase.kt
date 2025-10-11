package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.entity.FoodEntity
import javax.inject.Inject

class AddEntryUseCase @Inject constructor(
    private val entryDao: EntryDao
) : IAddEntryUseCase {
    override suspend fun insertNewEntry(foodEntity: FoodEntity) {
        entryDao.insertEntry(convertFoodEntityToEntryEntity(foodEntity))
    }
}
