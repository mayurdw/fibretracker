package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.entity.FoodEntryEntity

interface IGetEntryUseCase {
    suspend fun getAllEntries(): List<FoodEntryEntity>
}
