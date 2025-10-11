package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import java.util.Date

fun convertFoodEntityToEntryEntity(foodEntity: FoodEntity): FoodEntryEntity {
    return FoodEntryEntity(
        foodDisplayName = foodEntity.displayName,
        foodServingInGms = foodEntity.singleServingSizeInGm,
        fibreThisServingInMilliGms = foodEntity.fibreQuantityPerServingInMG,
        date = Date().time
    )
}
