package com.mayurdw.fibretracker.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayurdw.fibretracker.data.CommonFoods
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

@Composable
fun AddEntryView(modifier: Modifier = Modifier) {
    val foodItem = remember { mutableStateOf(CommonFoods[0].foodName) }
    val foodQuantity = remember { mutableStateOf(CommonFoods[0].foodAmountInGrams.toString()) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
        ) {
        TextField(
            value = foodItem.value,
            onValueChange = { newValue: String -> foodItem.value = newValue },
            label = { Text("Food Item") },
            placeholder = { Text("Chia") },
        )
        TextField(
            value = foodQuantity.value,
            onValueChange = { newValue: String -> foodQuantity.value = newValue },
            label = { Text("Quantity in Grams") },
            placeholder = { Text("Chia") },
        )

        Text(modifier = modifier, text = "Fibre Per MilliGram = ${CommonFoods[0].fiberPerMilliGrams}")

        Text(modifier = modifier, text = "Fiber Consumed = ${CommonFoods[0].totalFiberInFood / 1000}")

        Button(onClick = { }, content = { Text("Submit") } )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddEntryPreview() {
    FibreTrackerTheme {
        AddEntryView()
    }
}
