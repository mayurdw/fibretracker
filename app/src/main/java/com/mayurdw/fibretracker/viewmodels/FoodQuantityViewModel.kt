package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.usecase.IAddEntryUseCase
import com.mayurdw.fibretracker.data.usecase.IGetFoodUseCase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.viewmodels.UIState.Error
import com.mayurdw.fibretracker.viewmodels.UIState.Loading
import com.mayurdw.fibretracker.viewmodels.UIState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodQuantityViewModel @Inject constructor(
    private val getFoodUseCase: IGetFoodUseCase,
    private val entryUseCase: IAddEntryUseCase
) : ViewModel() {
    val foodState: StateFlow<UIState<FoodEntity>>
        field = MutableStateFlow<UIState<FoodEntity>>(Loading)

    val entryState: StateFlow<Boolean>
        field = MutableStateFlow<Boolean>(false)

    fun loadFoodDetails(id: Int) {
        viewModelScope.launch {
            foodState.emit(Loading)
            getFoodUseCase.getFoodById(id).collectLatest {
                if (it.isSuccess) {
                    foodState.emit(Success(it.getOrNull()!!))
                } else {
                    foodState.emit(Error)
                }
            }
        }
    }

    fun insertNewEntry(foodEntity: FoodEntity, foodQuantity: String) {
        viewModelScope.launch {
            foodState.emit(Loading)
            entryState.emit(false)
            val quantity = foodQuantity.toInt()
            if (foodEntity.singleServingSizeInGm != quantity) {
                val entity = foodEntity.copy(singleServingSizeInGm = quantity)
                entity.id = foodEntity.id
                entryUseCase.insertNewEntry(entity)
            } else {
                entryUseCase.insertNewEntry(foodEntity)
            }
            entryState.emit(true)
        }
    }
}
