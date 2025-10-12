package com.mayurdw.fibretracker.model.domain

data class DateData(
    val formattedDate: String,
    val foodItems: List<FoodListItem>,
    val fibreOfTheDay: String
)
