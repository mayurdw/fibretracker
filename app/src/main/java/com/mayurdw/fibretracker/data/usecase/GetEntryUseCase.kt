package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.domain.EntryData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.DateTimeUnit.Companion.DAY
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import javax.inject.Inject

class GetEntryUseCase @Inject constructor(
    private val entryDao: AppDao
) : IGetEntryUseCase {
    override suspend fun getCurrentDateEntryData(currentDate: LocalDate): Flow<List<EntryData>> =
        entryDao.getEntryData(currentDate, currentDate)

    override suspend fun checkYesterdaysDateEntryData(currentDate: LocalDate): Flow<Boolean> {
        val yesterdaysDate = currentDate.minus(1, DAY)

        return entryDao.checkIfEntryDataExists(yesterdaysDate, yesterdaysDate)
    }

    override suspend fun checkTomorrowsDateEntryData(currentDate: LocalDate): Flow<Boolean> {
        val tomorrowsDate = currentDate.plus(1, DAY)

        return entryDao.checkIfEntryDataExists(tomorrowsDate, tomorrowsDate)
    }
}
