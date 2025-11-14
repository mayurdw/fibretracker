package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.entity.FoodEntity

interface IAddEntryUseCase {
    suspend fun insertNewEntry(foodEntity: FoodEntity, fibreQuantity: Int)
}
