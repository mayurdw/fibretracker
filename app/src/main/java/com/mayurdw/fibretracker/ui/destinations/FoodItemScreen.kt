package com.mayurdw.fibretracker.ui.destinations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.ui.screens.AddFoodItemList
import com.mayurdw.fibretracker.ui.screens.core.LoadingScreen
import com.mayurdw.fibretracker.viewmodels.AddFoodEntryViewModel
import com.mayurdw.fibretracker.viewmodels.FoodEntryState


@Composable
fun AddFoodItemScreen(
    viewModel: AddFoodEntryViewModel = hiltViewModel(),
    onItemSelect: (food: FoodEntity) -> Unit
) {
    val entries by viewModel.entryState.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    viewModel.loadData()

    when (entries) {
        is FoodEntryState.Success -> {
            val data = entries as FoodEntryState.Success

            AddFoodItemList(
                foodItems = data.foodItems
            ) {
                onItemSelect(it)
            }
        }

        is FoodEntryState.Loading -> {
            LoadingScreen()
        }

        else -> {}
    }
}
