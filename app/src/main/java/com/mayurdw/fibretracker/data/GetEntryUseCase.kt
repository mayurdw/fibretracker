package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class GetEntryUseCase @Inject constructor(
    private val entryDao: EntryDao
) : IGetEntryUseCase {
    override suspend fun getAllEntries(): List<FoodEntryEntity> {
        return entryDao.getAll()
    }

    override suspend fun getCurrentDateEntries(currentDate: LocalDate): Flow<List<FoodEntryEntity>> {
        return entryDao.getEntries(currentDate.toEpochDays(), currentDate.toEpochDays())
    }
}
