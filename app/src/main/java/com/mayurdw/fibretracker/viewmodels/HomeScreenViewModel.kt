@file:OptIn(ExperimentalTime::class)

package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.GetEntryUseCase
import com.mayurdw.fibretracker.data.convertFoodEntryEntityToFoodListItem
import com.mayurdw.fibretracker.model.domain.DateData
import com.mayurdw.fibretracker.model.domain.FoodListItem
import com.mayurdw.fibretracker.model.domain.HomeData
import com.mayurdw.fibretracker.model.domain.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import javax.inject.Inject
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val getEntryUseCase: GetEntryUseCase
) : ViewModel() {
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
            val currentDate = Clock.System.todayIn(TimeZone.currentSystemDefault())

            homeStateFlow.emit(HomeState.Loading)
            with(getEntryUseCase) {
                combine(
                    getCurrentDateEntries(currentDate),
                    getYesterdaysDateEntries(currentDate),
                    getTomorrowsDateEntries(currentDate)
                ) { current, yesterday, tomorrow ->

                    val foodList = current.map { foodEntryEntity ->
                        launch(Dispatchers.Default) {
                            convertFoodEntryEntityToFoodListItem(foodEntryEntity = foodEntryEntity)
                        }
                    }

                }
            }
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
