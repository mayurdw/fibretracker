package com.mayurdw.fibretracker.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mayurdw.fibretracker.data.usecase.IAddFoodUseCase
import com.mayurdw.fibretracker.data.usecase.IGetFoodUseCase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class AddNewFoodViewModel @Inject constructor(
    private val getFoodUseCase: IGetFoodUseCase,
    private val foodUseCase: IAddFoodUseCase
) : ViewModel() {
    val uiState: StateFlow<UIState<FoodEntity>>
        field = MutableStateFlow<UIState<FoodEntity>>(UIState.Loading)


    fun getFoodById(foodId: Int) {
        viewModelScope.launch {
            val foodEntity = getFoodUseCase.getFoodById(foodId)

            uiState.emit(UIState.Success<FoodEntity>(foodEntity))
        }
    }

    fun isValid(foodName: String, foodServing: String, fibrePerServing: String): Boolean {
        return foodName.isNotBlank() && foodServing.isNotBlank() && fibrePerServing.isNotBlank()
    }

    fun isUpdated(
        data: FoodEntity,
        foodName: String,
        foodServing: String,
        fibrePerServing: String
    ): Boolean {
        return foodName != data.name ||
                foodServing.toInt() != data.singleServingSizeInGm ||
                fibrePerServing.toBigDecimal() != (data.fibrePerGram * data.singleServingSizeInGm.toBigDecimal())
    }

    fun updateFood(
        foodEntity: FoodEntity,
        newName: String,
        newServingSize: String,
        newFibrePerServing: String
    ) {
        viewModelScope.launch {
            if (isUpdated(foodEntity, newName, newServingSize, newFibrePerServing)) {
                val newEntity = foodEntity.copy(
                    name = newName,
                    singleServingSizeInGm = newServingSize.toInt(),
                    fibrePerMicroGram = getFibrePerMicroGram(
                        fibrePerServingSizeInGms = newFibrePerServing,
                        foodServingSize = newServingSize
                    )
                ).apply {
                    id = foodEntity.id
                }

                foodUseCase.upsertNewFood(newEntity)
            }
        }
    }

    private fun getFibrePerMicroGram(
        fibrePerServingSizeInGms: String,
        foodServingSize: String
    ): Int {
        return BigDecimal.valueOf(
            fibrePerServingSizeInGms.toDouble() / foodServingSize.toDouble() * 1_000_000
        ).toInt()
    }

    fun addNewFood(foodName: String, foodServingSize: String, fibrePerServingInGms: String) {
        viewModelScope.launch {
            if (foodName.isNotBlank() && foodServingSize.isNotBlank() && fibrePerServingInGms.isNotBlank()) {
                try {
                    val foodEntity = FoodEntity(
                        name = foodName,
                        singleServingSizeInGm = foodServingSize.toInt(),
                        fibrePerMicroGram = getFibrePerMicroGram(
                            foodServingSize = foodServingSize,
                            fibrePerServingSizeInGms = fibrePerServingInGms
                        )
                    )
                    foodUseCase.upsertNewFood(foodEntity)
                } catch (_: Exception) {

                }
            }
        }
    }
}
