package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.model.domain.FoodItem
import javax.inject.Inject

class FoodUseCase @Inject constructor(
    private val dao: FoodDao
) : IFoodUseCase {

    override suspend fun getFoods(): List<FoodItem> {
        val foods = dao.getAll()

        if (foods.isEmpty()) {
            CommonFoods.forEach { foodItem ->
                dao.insertFood(convertFoodItemToFoodEntity(foodItem))
            }
            return CommonFoods
        } else {
            val foodItems: List<FoodItem> = foods.map { food ->
                convertFoodEntityToFoodItem(food)
            }

            return foodItems
        }
    }

    override suspend fun insertNewFood(newFoodItem: FoodItem) {
        TODO("Not yet implemented")
    }
}
