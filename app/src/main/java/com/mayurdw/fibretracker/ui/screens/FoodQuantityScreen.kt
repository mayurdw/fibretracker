package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme
import com.mayurdw.fibretracker.viewmodels.FoodQuantityState
import com.mayurdw.fibretracker.viewmodels.FoodQuantityViewModel
import kotlinx.coroutines.flow.collectLatest

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
            FoodQuantityScreenContent(
                modifier,
                (state as FoodQuantityState.Success).food
            ) { selectedFoodItem, fibreQuantity, foodQuantity ->
                viewModel.insertNewEntry(selectedFoodItem, fibreQuantity, foodQuantity)
            }
        }


    }
}

@Composable
private fun FoodQuantityScreenContent(
    modifier: Modifier = Modifier,
    selectedFoodItem: FoodEntity,
    onSaveClick: (selectedFoodItem: FoodEntity, fibreQuantity: Int, foodQuantity: String) -> Unit
) {
    val foodQuantity =
        rememberTextFieldState(initialText = selectedFoodItem.singleServingSizeInGm.toString())
    var fibreQuantity =
        if (foodQuantity.text.isNotBlank()) {
            selectedFoodItem.fibreQuantityPerServingInMG * foodQuantity.text.toString()
                .toInt() / 1000
        } else {
            0
        }

    LaunchedEffect(foodQuantity) {
        snapshotFlow { foodQuantity.text.toString() }.collectLatest { newValue: String ->
            if (newValue.isNotBlank()) {
                fibreQuantity =
                    selectedFoodItem.fibreQuantityPerServingInMG * newValue.toInt() / 1000
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = selectedFoodItem.displayName,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
        TextField(
            state = foodQuantity,
            label = { Text("Quantity in Grams") },
            placeholder = { Text("${selectedFoodItem.singleServingSizeInGm}") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        )

        Text(
            modifier = modifier,
            text = "Fibre Per Gram = ${selectedFoodItem.fibreQuantityPerServingInMG / 1000.0} gm"
        )

        Text(
            modifier = modifier,
            text = "Fiber Consumed = $fibreQuantity"
        )

        Button(onClick = {
            onSaveClick(selectedFoodItem, fibreQuantity, foodQuantity.text.toString())
        }, content = { Text("Submit") })
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
private fun FoodQuantityScreenPreview() {
    FibreTrackerTheme {
        FoodQuantityScreenContent(
            selectedFoodItem = FoodEntity(
                "Test",
                40,
                35
            )
        ) { _, _, _ ->

        }
    }
}
