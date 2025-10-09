package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.IFoodUseCase
import com.mayurdw.fibretracker.model.domain.FoodItem
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
    val entryState: StateFlow<AddFoodEntryState>
        field = MutableStateFlow<AddFoodEntryState>(AddFoodEntryState.Loading)

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            entryState.emit(AddFoodEntryState.Loading)
            entryState.emit(AddFoodEntryState.Success(foodUseCase.getFoods()))
        }
    }
}

sealed interface AddFoodEntryState {
    object Error : AddFoodEntryState
    object Loading : AddFoodEntryState
    data class Success(val foodItems: List<FoodItem>) : AddFoodEntryState
}
