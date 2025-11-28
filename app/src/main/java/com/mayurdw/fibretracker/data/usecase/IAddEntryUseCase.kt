package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.model.entity.FoodEntity

interface IAddEntryUseCase {
    suspend fun insertNewEntry(foodEntity: FoodEntity)
}
