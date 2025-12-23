package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.domain.EntryData
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface IGetEntryUseCase {
    suspend fun getCurrentDateEntryData(currentDate: LocalDate): Flow<List<EntryData>>

    suspend fun getEntry(entryId: Int): Flow<EntryData>
}
