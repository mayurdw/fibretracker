package com.mayurdw.fibretracker.model.domain

data class HomeData(
    val hasPrevious: Boolean,
    val hasNext: Boolean,
    val dateData: DateData
) {
    data class DateData(
        val formattedDate: String,
        val foodItems: List<FoodListItem>,
        val fibreOfTheDay: String
    )

    data class FoodListItem(
        val id: Int,
        val foodName: String,
        val foodQuantity: String,
        val fibreThisMeal: String
    )
}

sealed interface HomeState {
    object Loading : HomeState
    object Error : HomeState
    data class Success(val data: HomeData) : HomeState
}
