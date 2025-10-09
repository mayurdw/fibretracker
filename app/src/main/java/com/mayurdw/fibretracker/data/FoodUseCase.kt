package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.model.domain.FoodItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodUseCase @Inject constructor(
    private val dao: FoodDao
) : IFoodUseCase {

    override suspend fun getFoods(): Flow<List<FoodItem>> {
        return flow {
            dao.getAll().collect { foods ->
                if (foods.isNotEmpty()) {
                    emit(foods.map {
                        withContext(Dispatchers.IO) {
                            convertFoodEntityToFoodItem(it)
                        }
                    })
                } else {
                    CommonFoods.onEach { foodItem ->
                        withContext(Dispatchers.IO) {
                            dao.insertFood(
                                convertFoodItemToFoodEntity(foodItem)
                            )
                        }
                    }

                    emit(CommonFoods)
                }
            }
        }
    }

    override suspend fun insertNewFood(newFoodItem: FoodItem) {
        TODO("Not yet implemented")
    }
}
