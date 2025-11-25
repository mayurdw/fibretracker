package com.mayurdw.fibretracker.data.helpers

import com.mayurdw.fibretracker.model.domain.FoodListItem
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun convertFoodEntityToEntryEntity(foodEntity: FoodEntity, fibreQuantity: Int): FoodEntryEntity {
    val date = Clock.System.todayIn(TimeZone.currentSystemDefault())

    return FoodEntryEntity(
        foodDisplayName = foodEntity.displayName,
        foodServingInGms = foodEntity.singleServingSizeInGm,
        fibreThisServingInMilliGms = fibreQuantity * 1000,
        date = date.toEpochDays()
    )
}

fun convertFoodEntryEntityToFoodListItem(foodEntryEntity: FoodEntryEntity): FoodListItem {
    return FoodListItem(
        id = foodEntryEntity.id,
        foodName = foodEntryEntity.foodDisplayName,
        foodQuantity =
            "${foodEntryEntity.foodServingInGms}"
    )
}
