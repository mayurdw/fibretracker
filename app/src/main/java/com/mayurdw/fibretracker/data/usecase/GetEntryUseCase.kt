package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.domain.EntryData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate
import javax.inject.Inject

class GetEntryUseCase @Inject constructor(
    private val entryDao: AppDao,
    private val dispatcher: CoroutineDispatcher
) : IGetEntryUseCase {
    override suspend fun getCurrentDateEntryData(currentDate: LocalDate): Flow<List<EntryData>> =
        channelFlow {
            withContext(dispatcher) {
                entryDao.getEntryData(currentDate, currentDate).collectLatest {
                    trySend(it)
                }
            }
        }

    override suspend fun getEntry(entryId: Int): Flow<EntryData> {
        return channelFlow {
            entryDao.getEntry(entryId).collectLatest {
                trySend(it)
            }
        }
    }
}
