package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity

interface IAddEntryUseCase {
    suspend fun insertNewEntry(foodEntity: FoodEntity)

    suspend fun insertNewEntry(foodEntryEntity: FoodEntryEntity)
}
