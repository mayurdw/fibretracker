package com.mayurdw.fibretracker.data.helpers

import com.mayurdw.fibretracker.model.domain.EntryData
import com.mayurdw.fibretracker.model.domain.HomeData.FoodListItem
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
fun convertFoodEntityToEntryEntity(foodEntity: FoodEntity): FoodEntryEntity {
    val date = Clock.System.todayIn(TimeZone.currentSystemDefault())

    return FoodEntryEntity(
        foodId = foodEntity.id,
        foodServingInGms = foodEntity.singleServingSizeInGm,
        date = date
    )
}

fun convertFoodEntryEntityToFoodListItem(entryData: EntryData): FoodListItem {
    return FoodListItem(
        id = entryData.id,
        foodName = entryData.name,
        foodQuantity =
            "${entryData.servingInGms}",
        fibreThisMeal = entryData.fibreConsumedInGms.toString()
    )
}
