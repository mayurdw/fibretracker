package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.usecase.IAddEntryUseCase
import com.mayurdw.fibretracker.data.usecase.IGetEntryUseCase
import com.mayurdw.fibretracker.model.domain.EntryData
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditFoodEntryViewModel @Inject constructor(
    private val entryUseCase: IGetEntryUseCase,
    private val addEntryUseCase: IAddEntryUseCase
) : ViewModel() {

    val selectedEntryFlow: StateFlow<EntryState>
        field = MutableStateFlow<EntryState>(EntryState.Loading)
    
    val saveSuccessful: StateFlow<Boolean>
        field = MutableStateFlow<Boolean>(false)

    fun getEntryData(selectedEntryId: Int) {
        viewModelScope.launch {
            val entry = entryUseCase.getEntry(selectedEntryId).firstOrNull()

            entry?.let {
                selectedEntryFlow.emit(EntryState.Success(entry))
            } ?: run {
                selectedEntryFlow.emit(EntryState.Error)
            }
        }
    }

    fun updateEntry(newFoodQuantity: String, entry: EntryData) {
        if (newFoodQuantity.toInt() != entry.servingInGms) {

            viewModelScope.launch(Dispatchers.IO) {
                addEntryUseCase.insertNewEntry(
                    FoodEntryEntity(
                        foodId = entry.foodId,
                        foodServingInGms = newFoodQuantity.toInt(),
                        date = entry.date
                    ).apply { id = entry.id }
                )

                saveSuccessful.emit(true)
            }

        }
    }

    fun isEdited(newValue: String?, entry: EntryData): Boolean {
        if (newValue.isNullOrBlank() && newValue?.toIntOrNull() == null)
            return false

        return entry.servingInGms != newValue.toInt()
    }
}

sealed interface EntryState {
    object Error : EntryState
    object Loading : EntryState
    data class Success(val entry: EntryData) : EntryState
}
