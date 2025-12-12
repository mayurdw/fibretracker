package com.mayurdw.fibretracker.ui.destinations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.mayurdw.fibretracker.ui.screens.AddNewFoodScreenLayout
import com.mayurdw.fibretracker.viewmodels.AddNewFoodViewModel

@Composable
fun AddNewFoodScreen(
    modifier: Modifier = Modifier,
    viewModel: AddNewFoodViewModel = hiltViewModel(),
    onActionCompleted: () -> Unit = { }
) {
    AddNewFoodScreenLayout(
        modifier = modifier
    ) { foodName: String, foodServing: String, fibrePerServing: String ->
        viewModel.addNewFood(foodName, foodServing, fibrePerServing)
        onActionCompleted()
    }
}
