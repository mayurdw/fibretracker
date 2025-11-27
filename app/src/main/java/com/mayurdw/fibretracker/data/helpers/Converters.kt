package com.mayurdw.fibretracker.data.helpers

import com.mayurdw.fibretracker.model.domain.HomeData.FoodListItem
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
        foodId = foodEntity.id,
        foodServingInGms = foodEntity.singleServingSizeInGm,
        date = date
    )
}

fun convertFoodEntryEntityToFoodListItem(foodEntryEntity: FoodEntryEntity): FoodListItem {
    return FoodListItem(
        id = foodEntryEntity.id,
        foodName = "",
        foodQuantity =
            "${foodEntryEntity.foodServingInGms}"
    )
}
