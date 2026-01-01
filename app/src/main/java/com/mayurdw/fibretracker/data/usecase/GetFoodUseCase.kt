package com.mayurdw.fibretracker.data.usecase

import android.content.res.Resources
import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.model.entity.FoodEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFoodUseCase @Inject constructor(
    private val dao: AppDao,
    private val dispatcher: CoroutineDispatcher
) : IGetFoodUseCase {
    override suspend fun getFoods(): Flow<List<FoodEntity>> {
        return channelFlow {
            withContext(dispatcher) {
                dao.getAllFoods().distinctUntilChanged().collectLatest {
                    if (it.isEmpty()) {
                        CommonFoods.forEach { foodItem ->
                            dao.upsertNewFood(foodItem)
                        }
                        trySend(CommonFoods)
                    } else {
                        trySend(it)
                    }
                }
            }
        }
    }

    override suspend fun getFoodById(id: Int): Flow<Result<FoodEntity>> {
        return channelFlow {
            withContext(dispatcher) {
                dao.getFoodById(id)?.let {
                    trySend(Result.success(it))
                } ?: run {
                    trySend(Result.failure(Resources.NotFoundException()))
                }
            }
        }
    }
}
