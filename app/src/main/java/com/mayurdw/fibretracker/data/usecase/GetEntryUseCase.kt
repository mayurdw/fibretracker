package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.domain.EntryData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class GetEntryUseCase @Inject constructor(
    private val entryDao: AppDao
) : IGetEntryUseCase {
    override suspend fun getCurrentDateEntryData(currentDate: LocalDate): Flow<List<EntryData>> =
        entryDao.getEntryData(currentDate, currentDate)

    override suspend fun getEntry(entryId: Int): Flow<EntryData> {
        return entryDao.getEntry(entryId)
    }
}
