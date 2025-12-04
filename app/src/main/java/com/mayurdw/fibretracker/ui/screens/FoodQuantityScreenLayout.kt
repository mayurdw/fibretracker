package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FoodQuantityScreenLayout(
    modifier: Modifier = Modifier,
    selectedFoodItem: FoodEntity,
    onSaveClick: (selectedFoodItem: FoodEntity, foodQuantity: String) -> Unit
) {
    val foodQuantity =
        rememberTextFieldState(initialText = selectedFoodItem.singleServingSizeInGm.toString())
    var fibreQuantity =
        if (foodQuantity.text.isNotBlank()) {
            selectedFoodItem.fibrePerGram * foodQuantity.text.toString().toBigDecimal()
        } else {
            0
        }

    LaunchedEffect(foodQuantity) {
        snapshotFlow { foodQuantity.text.toString() }.collectLatest { newValue: String ->
            if (newValue.isNotBlank()) {
                fibreQuantity =
                    selectedFoodItem.fibrePerGram * newValue.toBigDecimal()
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = selectedFoodItem.name,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface
        )

        TextField(
            modifier = modifier.fillMaxWidth(),
            state = foodQuantity,
            label = { Text("Quantity in Grams") },
            placeholder = { Text("${selectedFoodItem.singleServingSizeInGm}") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            lineLimits = TextFieldLineLimits.SingleLine,
        )

        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = modifier,
                text = "Fibre Per Gram",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = modifier.weight(0.5f))

            Text(
                modifier = modifier,
                text = "${selectedFoodItem.fibrePerGram} gm",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }


        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = modifier,
                text = "Fibre consumed",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = modifier.weight(0.5f))

            Text(
                modifier = modifier,
                text = "$fibreQuantity gm",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        Spacer(modifier = modifier.weight(0.5f))

        Button(
            modifier = modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            onClick = {
                onSaveClick(selectedFoodItem, foodQuantity.text.toString())
            },
            enabled = foodQuantity.text.isNotBlank(),
            content = { Text("Submit") })
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
private fun FoodQuantityScreenPreview() {
    FibreTrackerTheme {
        FoodQuantityScreenLayout(
            selectedFoodItem = FoodEntity(
                "Test",
                40,
                35
            )
        ) { _, _ ->

        }
    }
}
