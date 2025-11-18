package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

@Composable
fun AddNewFoodScreen(
    modifier: Modifier = Modifier
) {
    AddNewFoodContent(
        modifier = modifier
    ) {
        
    }
}

@Composable
private fun AddNewFoodContent(
    modifier: Modifier = Modifier,
    onAddButton: () -> Unit,
) {
    val foodNameState = rememberTextFieldState("")
    val foodServingSizeState = rememberTextFieldState("")
    val fibrePerServingInGms = rememberTextFieldState("")
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            modifier = modifier,
            state = foodNameState,
            placeholder = { Text("Food Name") },
            label = { Text("Enter Food Name") }
        )

        TextField(
            modifier = modifier,
            state = foodServingSizeState,
            placeholder = { Text("0") },
            label = { Text("Enter Food Serving Size") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        TextField(
            modifier = modifier,
            state = fibrePerServingInGms,
            placeholder = { Text("0") },
            label = { Text("Fibre In Grams") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(
            modifier.weight(1f)
        )

        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = onAddButton
        ) {
            Text("Add Food")
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 480)
@Composable
private fun AddNewFoodScreenPreview() {
    FibreTrackerTheme {
        AddNewFoodContent { }
    }
}
