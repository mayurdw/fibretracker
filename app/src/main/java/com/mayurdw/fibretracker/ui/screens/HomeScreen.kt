package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mayurdw.fibretracker.R
import com.mayurdw.fibretracker.model.domain.DateData
import com.mayurdw.fibretracker.model.domain.FoodListItem
import com.mayurdw.fibretracker.model.domain.HomeData
import com.mayurdw.fibretracker.model.domain.HomeState
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme
import com.mayurdw.fibretracker.viewmodels.HomeScreenViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
) {
    val homeState by viewModel.homeStateFlow.collectAsStateWithLifecycle(minActiveState = Lifecycle.State.RESUMED)
    viewModel.getLatestData()

    when (homeState) {
        is HomeState.Success -> {
            val homeData = homeState as HomeState.Success
            HomeScreenLayout(
                modifier = modifier,
                homeData = homeData.data,
                onNextClicked = { viewModel.onDateChanged(false) },
                onPreviousClicked = { viewModel.onDateChanged(true) })
        }

        is HomeState.Loading -> {
            LoadingScreen(modifier)
        }

        else -> {

        }
    }
}


@Composable
fun HomeScreenLayout(
    modifier: Modifier,
    homeData: HomeData,
    onNextClicked: () -> Unit,
    onPreviousClicked: () -> Unit
) {
    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.Top) {
        DatePicker(
            modifier,
            homeData.hasNext,
            homeData.hasPrevious,
            homeData.dateData.formattedDate,
            onNextClicked,
            onPreviousClicked
        )

        Spacer(
            modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        FibreValue(modifier, homeData.dateData.fibreOfTheDay)

        Spacer(
            modifier
                .fillMaxWidth()
                .height(36.dp)
        )

        FoodItems(
            modifier,
            homeData.dateData.foodItems
        )

    }
}

@Composable
private fun FibreValue(
    modifier: Modifier,
    value: String,
    units: String = "gm"
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = modifier,
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            text = value
        )

        Spacer(modifier.size(8.dp))

        Text(
            modifier = modifier,
            text = units
        )
    }
}

@Composable
private fun DatePicker(
    modifier: Modifier,
    hasNext: Boolean,
    hasPrevious: Boolean,
    formattedDate: String,
    onNextClicked: () -> Unit,
    onPreviousClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            modifier = modifier,
            onClick = onPreviousClicked, enabled = hasPrevious
        ) {
            Icon(
                modifier = modifier,
                painter = painterResource(R.drawable.previous),
                contentDescription = null,
                tint = if (hasPrevious) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryFixedDim
            )
        }

        Text(
            modifier = modifier, text = formattedDate
        )

        IconButton(onClick = onNextClicked, enabled = hasNext) {
            Icon(
                modifier = modifier,
                painter = painterResource(R.drawable.next),
                contentDescription = null,
                tint = if (hasNext) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryFixedDim
            )
        }
    }
}


@Composable
fun FoodItems(modifier: Modifier = Modifier, foodItems: List<FoodListItem>) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
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
            modifier = Modifier,
            homeData = HomeData(
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
            ),
            onNextClicked = {},
            onPreviousClicked = {}
        )
    }
}
