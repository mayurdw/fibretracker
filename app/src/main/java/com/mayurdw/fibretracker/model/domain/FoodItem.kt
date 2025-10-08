package com.mayurdw.fibretracker.model.domain

data class FoodItem(
    val foodName: String,
    var foodAmountInGrams: Int,
    val fiberPerMilliGrams: Int
)
