@file:OptIn(ExperimentalTime::class)

package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.usecase.GetEntryUseCase
import com.mayurdw.fibretracker.data.helpers.convertFoodEntryEntityToFoodListItem
import com.mayurdw.fibretracker.model.domain.DateData
import com.mayurdw.fibretracker.model.domain.FoodListItem
import com.mayurdw.fibretracker.model.domain.HomeData
import com.mayurdw.fibretracker.model.domain.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
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
                        convertFoodEntryEntityToFoodListItem(foodEntryEntity = foodEntryEntity)
                    }
                    val dateFormat = LocalDate.Format {
                        day()
                        char('/')
                        monthNumber()
                        char('/')
                        yearTwoDigits(2000)
                    }
                    val date = currentDate.format(dateFormat)
                    var totalFibre = 0
                    current.forEach { totalFibre += it.fibreThisServingInMilliGms }

                    HomeState.Success(
                        HomeData(
                            hasNext = tomorrow.count() > 0,
                            hasPrevious = yesterday.count() > 0,
                            dateData = DateData(
                                formattedDate = date,
                                fibreOfTheDay =
                                    "${totalFibre / 1000.00}",
                                foodItems = foodList
                            )
                        )
                    )
                }.collect {
                    homeStateFlow.emit(it)
                }
            }
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
