package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class GetEntryUseCase @Inject constructor(
    private val entryDao: AppDao
) : IGetEntryUseCase {
    override suspend fun getAllEntries(): List<FoodEntryEntity> {
        return entryDao.getAllEntries()
    }

    override suspend fun getCurrentDateEntries(currentDate: LocalDate): Flow<List<FoodEntryEntity>> {
        return entryDao.getEntries(currentDate.toEpochDays(), currentDate.toEpochDays())
    }
}
