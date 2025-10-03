@file:OptIn(ExperimentalMaterial3Api::class)

package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme
import com.mayurdw.fibretracker.viewmodels.FoodListItem
import com.mayurdw.fibretracker.viewmodels.HomeScreenViewModel
import com.mayurdw.fibretracker.viewmodels.HomeState
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = viewModel(),
    onAddButtonClicked: () -> Unit = {}
) {
    val homeState by viewModel.homeStateFlow.collectAsState()
    viewModel.getLatestData()

    when (homeState) {
        is HomeState.Success -> {
            val homeData = homeState as HomeState.Success
            FoodItems(foodItems = homeData.data.dateData.foodItems)
        }

        else -> {

        }
    }
}

@Composable
fun FoodItems(modifier: Modifier = Modifier, foodItems: List<FoodListItem>) {
    LazyColumn(modifier = modifier.fillMaxWidth()) {
        items(items = foodItems, key = { item: FoodListItem -> item.id }) {
            Row(
                modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(it.foodName)
                Text(
                    "${it.foodQuantity} gm"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFoodItems() {
    FibreTrackerTheme {
        FoodItems(
            foodItems = listOf(
                FoodListItem(id = 1, foodQuantity = "34.9", foodName = "Potato"),
                FoodListItem(id = 2, foodQuantity = "15.23", foodName = "Chia")
            )
        )
    }
}
