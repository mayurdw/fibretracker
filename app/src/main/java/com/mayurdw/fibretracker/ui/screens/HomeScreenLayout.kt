package com.mayurdw.fibretracker.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mayurdw.fibretracker.R
import com.mayurdw.fibretracker.model.domain.HomeData
import com.mayurdw.fibretracker.model.domain.HomeData.DateData
import com.mayurdw.fibretracker.model.domain.HomeData.FoodListItem
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

@Composable
fun HomeScreenLayout(
    modifier: Modifier,
    homeData: HomeData,
    onNextClicked: () -> Unit,
    onPreviousClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Top
    ) {
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
                .height(48.dp)
        )

        FibreValue(modifier, homeData.dateData.fibreOfTheDay)

        Spacer(
            modifier
                .height(48.dp)
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
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            text = value
        )

        Spacer(modifier.size(8.dp))

        Text(
            modifier = modifier,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.8f),
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
                tint = if (hasPrevious) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.inverseOnSurface
            )
        }

        Text(
            modifier = modifier,
            text = formattedDate,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        IconButton(onClick = onNextClicked, enabled = hasNext) {
            Icon(
                modifier = modifier,
                painter = painterResource(R.drawable.next),
                contentDescription = null,
                tint = if (hasNext) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }
}


@Composable
fun FoodItems(modifier: Modifier = Modifier, foodItems: List<FoodListItem>) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items = foodItems, key = { item: FoodListItem -> item.id }) {
            Card(
                colors = CardDefaults.elevatedCardColors().copy(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.secondary
                ),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        it.foodName,
                        style = MaterialTheme.typography.bodyLarge,
                    )

                    Column(
                        modifier = modifier.heightIn(min = 48.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "${it.foodQuantity} gm",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            "${it.fibreThisMeal} gm",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.inverseSurface
                        )
                    }
                }
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
                    fibreOfTheDay = "0.8",
                    foodItems = listOf(
                        FoodListItem(
                            id = 1,
                            foodQuantity = "34.9",
                            foodName = "Potato",
                            fibreThisMeal = "0.3"
                        ),
                        FoodListItem(
                            id = 2,
                            foodQuantity = "15.23",
                            foodName = "Chia",
                            fibreThisMeal = "0.5"
                        )
                    )
                )
            ),
            onNextClicked = {},
            onPreviousClicked = {}
        )
    }
}
