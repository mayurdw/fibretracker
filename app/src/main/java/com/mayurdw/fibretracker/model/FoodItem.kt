package com.mayurdw.fibretracker.model

data class FoodItem(
    val foodName: String,
    var foodAmountInGrams: Int,
    val fiberPerMilliGrams: Int
) {
    val totalFiberInFood: Int
        get() = foodAmountInGrams * fiberPerMilliGrams
}
