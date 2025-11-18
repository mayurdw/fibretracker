package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.IFoodUseCase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewFoodViewModel @Inject constructor(
    private val foodUseCase: IFoodUseCase
) : ViewModel() {
    fun addNewFood(foodName: String, foodServingSize: String, fibrePreServingInGms: String) {
        viewModelScope.launch {
            if (foodName.isNotBlank() && foodServingSize.isNotBlank() && fibrePreServingInGms.isNotBlank()) {
                try {
                    val foodEntity = FoodEntity(
                        displayName = foodName,
                        singleServingSizeInGm = foodServingSize.toInt(),
                        fibreQuantityPerServingInMG = fibrePreServingInGms.toInt() * 1000
                    )
                    foodUseCase.insertNewFood(foodEntity)
                } catch (_: Exception) {

                }
            }
        }
    }
}
