package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import javax.inject.Inject

class GetEntryUseCase @Inject constructor(
    private val entryDao: EntryDao
) : IGetEntryUseCase {
    override suspend fun getAllEntries(): List<FoodEntryEntity> {
        return entryDao.getAll()
    }
}
