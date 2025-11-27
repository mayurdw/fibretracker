package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

interface IGetEntryUseCase {
    suspend fun getCurrentDateEntries(currentDate: LocalDate): Flow<List<FoodEntryEntity>>

    suspend fun getYesterdaysDateEntries(currentDate: LocalDate): Flow<List<FoodEntryEntity>> {
        return getCurrentDateEntries(currentDate = currentDate.minus(1, DateTimeUnit.DAY))
    }

    suspend fun getTomorrowsDateEntries(currentDate: LocalDate): Flow<List<FoodEntryEntity>> {
        return getCurrentDateEntries(currentDate = currentDate.plus(1, DateTimeUnit.DAY))
    }
}
