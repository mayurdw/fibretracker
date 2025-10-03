package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mayurdw.fibretracker.R
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme
import com.mayurdw.fibretracker.viewmodels.DateData
import com.mayurdw.fibretracker.viewmodels.FoodListItem
import com.mayurdw.fibretracker.viewmodels.HomeData
import com.mayurdw.fibretracker.viewmodels.HomeScreenViewModel
import com.mayurdw.fibretracker.viewmodels.HomeState

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
            HomeScreenLayout(modifier = modifier, homeData.data)
        }

        else -> {

        }
    }
}


@Composable
fun HomeScreenLayout(modifier: Modifier, homeData: HomeData) {
    Column(modifier = modifier.fillMaxSize()) {
        Row(modifier = modifier.fillMaxWidth().padding(top = 16.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.Bottom) {
            IconButton(onClick = {}, enabled = homeData.hasPrevious) {
                Icon(painterResource(R.drawable.previous), contentDescription = null)
            }

            Text(modifier = modifier, text = homeData.dateData.formattedDate)

            IconButton(onClick = {}, enabled = homeData.hasNext) {
                Icon(painterResource(R.drawable.next), contentDescription = null)
            }
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
private fun PreviewHomeScreenLayout() {
    FibreTrackerTheme {
        HomeScreenLayout(
            Modifier, HomeData(
                hasPrevious = false,
                hasNext = true,
                dateData = DateData(
                    "29/5/25",
                    fibreOfTheDay = "29.4",
                    foodItems = listOf(
                        FoodListItem(id = 1, foodQuantity = "34.9", foodName = "Potato"),
                        FoodListItem(id = 2, foodQuantity = "15.23", foodName = "Chia")
                    )
                )
            )
        )
    }
}
