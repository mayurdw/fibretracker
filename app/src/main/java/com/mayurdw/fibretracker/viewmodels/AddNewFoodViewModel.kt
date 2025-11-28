package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.usecase.IAddFoodUseCase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class AddNewFoodViewModel @Inject constructor(
    private val foodUseCase: IAddFoodUseCase
) : ViewModel() {
    fun addNewFood(foodName: String, foodServingSize: String, fibrePerServingInGms: String) {
        viewModelScope.launch {
            if (foodName.isNotBlank() && foodServingSize.isNotBlank() && fibrePerServingInGms.isNotBlank()) {
                try {
                    val fibrePerMicroGram: BigDecimal = BigDecimal.valueOf(
                        fibrePerServingInGms.toDouble() / foodServingSize.toDouble() * 1_000_000
                    )
                    val foodEntity = FoodEntity(
                        name = foodName,
                        singleServingSizeInGm = foodServingSize.toInt(),
                        fibrePerMicroGram = fibrePerMicroGram.toInt()
                    )
                    foodUseCase.insertNewFood(foodEntity)
                } catch (_: Exception) {

                }
            }
        }
    }
}
