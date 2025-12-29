package com.mayurdw.fibretracker.data.usecase

import android.content.res.Resources
import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.model.entity.FoodEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetFoodUseCase @Inject constructor(
    private val dao: AppDao
) : IGetFoodUseCase {
    override suspend fun getFoods(): List<FoodEntity> {
        val foods = dao.getAllFoods()

        if (foods.isEmpty()) {
            CommonFoods.forEach { foodItem ->
                dao.upsertNewFood(foodItem)
            }
            return CommonFoods
        } else {
            return foods
        }
    }

    override suspend fun getFoodById(id: Int): Flow<Result<FoodEntity>> {
        return flow {
            dao.getFoodById(id)?.let {
                emit(Result.success(it))
            } ?: run {
                emit(Result.failure(Resources.NotFoundException()))
            }
        }
    }
}
