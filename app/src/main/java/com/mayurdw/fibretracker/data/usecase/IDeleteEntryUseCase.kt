package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.domain.EntryData

interface IDeleteEntryUseCase {
    suspend fun deleteEntry(entity: EntryData)
}
