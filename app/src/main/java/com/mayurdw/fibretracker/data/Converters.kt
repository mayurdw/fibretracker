package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.domain.FoodItem
import com.mayurdw.fibretracker.model.entity.FoodEntity

fun convertFoodItemToFoodEntity(foodItem: FoodItem): FoodEntity {
    return FoodEntity(
        displayName = foodItem.foodName,
        fibreQuantityPerServingInMG = foodItem.fiberPerMilliGrams.toString(),
        singleServingSizeInGm = foodItem.foodAmountInGrams.toString()
    )
}

fun convertFoodEntityToFoodItem(foodEntity: FoodEntity): FoodItem {
    return FoodItem(
        foodName = foodEntity.displayName,
        fiberPerMilliGrams = foodEntity.fibreQuantityPerServingInMG.toInt(),
        foodAmountInGrams = foodEntity.singleServingSizeInGm.toInt()
    )
}
