package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.usecase.IFoodUseCase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFoodEntryViewModel @Inject constructor(
    private val foodUseCase: IFoodUseCase
) : ViewModel() {
    val entryState: StateFlow<FoodEntryState>
        field = MutableStateFlow<FoodEntryState>(FoodEntryState.Loading)

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            entryState.emit(FoodEntryState.Loading)
            entryState.emit(FoodEntryState.Success(foodUseCase.getFoods()))
        }
    }
}

sealed interface FoodEntryState {
    object Error : FoodEntryState
    object Loading : FoodEntryState
    data class Success(val foodItems: List<FoodEntity>) : FoodEntryState
}
