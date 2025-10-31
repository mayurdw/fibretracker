package com.mayurdw.fibretracker.model.domain

data class HomeData(
    val hasPrevious: Boolean,
    val hasNext: Boolean,
    val dateData: DateData
)

sealed interface HomeState {
    object Loading : HomeState
    object Error : HomeState
    data class Success(val data: HomeData) : HomeState
}
