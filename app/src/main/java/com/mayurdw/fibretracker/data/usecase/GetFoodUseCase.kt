package com.mayurdw.fibretracker.data.usecase

import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.model.entity.FoodEntity
import javax.inject.Inject

class GetFoodUseCase @Inject constructor(
    private val dao: AppDao
) : IGetFoodUseCase {
    override suspend fun getFoods(): List<FoodEntity> {
        val foods = dao.getAllFoods()

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
}
