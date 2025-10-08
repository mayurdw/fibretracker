package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.domain.FoodItem
import com.mayurdw.fibretracker.model.entity.Food

fun convertFoodItemToFoodEntity(foodItem: FoodItem): Food {
    return Food(
        displayName = foodItem.foodName,
        fibreQuantityPerServingInMG = foodItem.fiberPerMilliGrams.toString(),
        singleServingSizeInGm = foodItem.foodAmountInGrams.toString(),
        id = -1
    )
}

fun convertFoodEntityToFoodItem(food: Food): FoodItem {
    return FoodItem(
        foodName = food.displayName,
        fiberPerMilliGrams = food.fibreQuantityPerServingInMG.toInt(),
        foodAmountInGrams = food.singleServingSizeInGm.toInt()
    )
}
