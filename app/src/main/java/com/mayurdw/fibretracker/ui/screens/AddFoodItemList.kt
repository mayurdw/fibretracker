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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mayurdw.fibretracker.data.CommonFoods
import com.mayurdw.fibretracker.model.FoodItem
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme
import com.mayurdw.fibretracker.viewmodels.AddFoodEntryState
import com.mayurdw.fibretracker.viewmodels.AddFoodEntryViewModel


@Composable
fun AddFoodItemLayout(
    viewModel: AddFoodEntryViewModel = viewModel(),
    onItemSelect: () -> Unit
) {
    val entries by viewModel.entryState.collectAsState()
    viewModel.loadData()

    when (entries) {
        is AddFoodEntryState.Success -> {
            val data = entries as AddFoodEntryState.Success

            AddFoodItemList(
                foodItems = data.foodItems
            ) {
                onItemSelect()
            }
        }

        else -> {}
    }
}

@Composable
fun AddFoodItemList(
    modifier: Modifier = Modifier,
    foodItems: List<FoodItem>,
    onItemSelect: (FoodItem) -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = foodItems,
            key = { index: Int, foodItem: FoodItem -> foodItem.foodName }
        ) { index: Int, foodItem: FoodItem ->

            FoodItem(modifier, foodItem, onItemSelect)
            if (index < foodItems.lastIndex)
                HorizontalDivider(modifier, thickness = 2.dp)
        }
    }
}

@Composable
fun FoodItem(modifier: Modifier = Modifier, foodItem: FoodItem, onItemSelect: (FoodItem) -> Unit) {
    Text(
        text = foodItem.foodName,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 32.dp)
            .clickable(
                enabled = true,
                onClick = { onItemSelect(foodItem) })
    )
}

@Preview(showBackground = true)
@Composable
private fun AddFoodItemListPreview() {
    FibreTrackerTheme {
        AddFoodItemList(
            foodItems = CommonFoods,
            onItemSelect = {}
        )
    }
}
