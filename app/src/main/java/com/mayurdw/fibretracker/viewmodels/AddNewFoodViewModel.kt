package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.usecase.IFoodUseCase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNewFoodViewModel @Inject constructor(
    private val foodUseCase: IFoodUseCase
) : ViewModel() {
    fun addNewFood(foodName: String, foodServingSize: String, fibrePerServingInGms: String) {
        viewModelScope.launch {
            if (foodName.isNotBlank() && foodServingSize.isNotBlank() && fibrePerServingInGms.isNotBlank()) {
                try {
                    val foodEntity = FoodEntity(
                        name = foodName,
                        singleServingSizeInGm = foodServingSize.toInt(),
                        // TODO: This is wrong nowj
                        fibrePerMicroGram = (fibrePerServingInGms.toFloat() * 1000).toInt()
                    )
                    foodUseCase.insertNewFood(foodEntity)
                } catch (_: Exception) {

                }
            }
        }
    }
}
