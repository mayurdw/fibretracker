package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.ui.screens.core.FoodCardView
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme
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
