package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mayurdw.fibretracker.model.domain.CommonFoods
import com.mayurdw.fibretracker.viewmodels.FoodQuantityViewModel

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
fun FoodQuantityScreen(
    modifier: Modifier = Modifier,
    selectedFood: Int = -1,
    viewModel: FoodQuantityViewModel = hiltViewModel()
) {
    val selectedFoodItem = CommonFoods[0]
    val foodQuantity: MutableState<String> =
        remember { mutableStateOf(selectedFoodItem.singleServingSizeInGm.toString()) }

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
            value = foodQuantity.value,
            onValueChange = { newValue: String ->
                foodQuantity.value = newValue
            },
            label = { Text("Quantity in Grams") },
            placeholder = { Text("${selectedFoodItem.singleServingSizeInGm}") },
        )

        Text(
            modifier = modifier,
            text = "Fibre Per MilliGram = ${selectedFoodItem.fibreQuantityPerServingInMG}"
        )

        Text(
            modifier = modifier,
            text = "Fiber Consumed = ${selectedFoodItem.fibreQuantityPerServingInMG * foodQuantity.value.toInt() / 1000}"
        )

        Button(onClick = { }, content = { Text("Submit") })
    }
}
