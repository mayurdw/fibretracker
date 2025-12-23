package com.mayurdw.fibretracker.ui.destinations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayurdw.fibretracker.ui.screens.FoodQuantityScreenLayout
import com.mayurdw.fibretracker.ui.screens.core.LoadingScreen
import com.mayurdw.fibretracker.viewmodels.FoodQuantityState
import com.mayurdw.fibretracker.viewmodels.FoodQuantityViewModel

@Composable
fun FoodQuantityScreen(
    modifier: Modifier = Modifier,
    selectedFood: Int = -1,
    viewModel: FoodQuantityViewModel = hiltViewModel(),
    saveSuccessful: () -> Unit = {}
) {
    val state by viewModel.foodState.collectAsStateWithLifecycle(Lifecycle.State.RESUMED)
    val savedState by viewModel.entryState.collectAsStateWithLifecycle(Lifecycle.State.RESUMED)

    LaunchedEffect(viewModel) {
        viewModel.loadFoodDetails(selectedFood)
    }

    when (savedState) {
        true -> saveSuccessful()
    }

    when (state) {
        is FoodQuantityState.Loading -> {
            LoadingScreen()
        }

        is FoodQuantityState.Success -> {
            val foodEntity = (state as FoodQuantityState.Success).food

            FoodQuantityScreenLayout(
                modifier,
                foodName = foodEntity.name,
                fibrePerGram = foodEntity.fibrePerGram,
                singleServingSizeInGm = foodEntity.singleServingSizeInGm,
                buttonEnabled = { !it.isNullOrBlank() }
            ) { foodQuantity ->
                viewModel.insertNewEntry(foodEntity, foodQuantity)
            }
        }


    }
}
