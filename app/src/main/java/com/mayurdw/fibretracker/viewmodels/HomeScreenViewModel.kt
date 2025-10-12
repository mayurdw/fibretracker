package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.model.domain.DateData
import com.mayurdw.fibretracker.model.domain.FoodListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor() : ViewModel() {
    val homeStateFlow: StateFlow<HomeState>
        field = MutableStateFlow<HomeState>(HomeState.Loading)

    private var index: Int = -1

    private val dates: List<DateData> = listOf(
        DateData(
            formattedDate = "28/9/25",
            fibreOfTheDay = "12",
            foodItems = listOf(
                FoodListItem(
                    id = 8,
                    foodName = "Potato",
                    foodQuantity = "30"
                )
            )
        ),
        DateData(
            formattedDate = "29/9/25",
            fibreOfTheDay = "23",
            foodItems = listOf(
                FoodListItem(
                    id = 9,
                    foodName = "Chia",
                    foodQuantity = "15"
                )
            )
        ),
        DateData(
            formattedDate = "30/9/25",
            fibreOfTheDay = "0",
            foodItems = listOf(
                FoodListItem(
                    id = 19,
                    foodName = "Yogurt",
                    foodQuantity = "34gm"
                )
            )
        )
    )

    suspend fun emitState() {
        homeStateFlow.emit(
            HomeState.Success(
                data = HomeData(
                    hasPrevious = index > 0,
                    hasNext = dates.size - 1 > index,
                    dateData = dates[index]
                )
            )
        )
    }

    fun getLatestData() {
        viewModelScope.launch {
            if (index < 0)
                index = dates.size - 1
            emitState()
        }
    }

    fun onDateChanged(isPrevious: Boolean) {
        viewModelScope.launch {
            if (isPrevious) {
                if (index > 0)
                    index--
            } else {
                if (dates.size - 1 > index) {
                    index++
                }
            }
            emitState()
        }
    }

}

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
