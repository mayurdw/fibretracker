package com.mayurdw.fibretracker.data

import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.model.entity.FoodEntity
import javax.inject.Inject

class FoodUseCase @Inject constructor(
    private val dao: FoodDao
) : IFoodUseCase {

    override suspend fun getFoods(): List<FoodEntity> {
        val foods = dao.getAll()

        if (foods.isEmpty()) {
            CommonFoods.forEach { foodItem ->
                dao.insertFood(foodItem)
            }
            return CommonFoods
        } else {
            return foods
        }
    }

    override suspend fun getFoodById(id: Int): FoodEntity {
        return dao.getFoodById(id)
    }

    override suspend fun insertNewFood(newFoodItem: FoodEntity) {
        TODO("Not yet implemented")
    }
}
