package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.usecase.IAddEntryUseCase
import com.mayurdw.fibretracker.data.usecase.IGetFoodUseCase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodQuantityViewModel @Inject constructor(
    private val getFoodUseCase: IGetFoodUseCase,
    private val entryUseCase: IAddEntryUseCase
) : ViewModel() {
    val foodState: StateFlow<FoodQuantityState>
        field = MutableStateFlow<FoodQuantityState>(FoodQuantityState.Loading)

    val entryState: StateFlow<Boolean>
        field = MutableStateFlow<Boolean>(false)

    fun loadFoodDetails(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            foodState.emit(FoodQuantityState.Loading)
            foodState.emit(FoodQuantityState.Success(getFoodUseCase.getFoodById(id)))
        }
    }

    fun insertNewEntry(foodEntity: FoodEntity, foodQuantity: String) {
        viewModelScope.launch(Dispatchers.IO) {
            foodState.emit(FoodQuantityState.Loading)
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

sealed interface FoodQuantityState {
    object Error : FoodQuantityState
    object Loading : FoodQuantityState
    data class Success(val food: FoodEntity) : FoodQuantityState
}
