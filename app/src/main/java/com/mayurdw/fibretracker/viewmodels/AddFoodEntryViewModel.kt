package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.model.domain.FoodItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFoodEntryViewModel @Inject constructor() : ViewModel() {
    val entryState: StateFlow<AddFoodEntryState>
        field = MutableStateFlow<AddFoodEntryState>(AddFoodEntryState.Loading)

    fun loadData() {
        viewModelScope.launch {
            entryState.emit(AddFoodEntryState.Success(foodItems = CommonFoods))
        }
    }


}

sealed interface AddFoodEntryState {
    object Error : AddFoodEntryState
    object Loading : AddFoodEntryState
    data class Success(val foodItems: List<FoodItem>) : AddFoodEntryState
}
