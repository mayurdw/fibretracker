package com.mayurdw.fibretracker.ui.screens.core

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.mayurdw.fibretracker.ui.destinations.AddFoodItem
import com.mayurdw.fibretracker.ui.destinations.AddNewFoodItem
import com.mayurdw.fibretracker.ui.destinations.Destinations
import com.mayurdw.fibretracker.ui.destinations.Home
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

@Composable
fun TrackerBottomBar(
    modifier: Modifier = Modifier,
    items: List<TrackerBottomBarButton>
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .background(MaterialTheme.colorScheme.primaryContainer),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        for (i in items) {
            Column(
                modifier = Modifier.clickable(
                    enabled = true, onClick = {

                    }
                ),
                horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = i.icon,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = i.label,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@PreviewLightDark
@Composable
fun PreviewTrackerBottomBar() {
    FibreTrackerTheme {
        TrackerBottomBar(
            items = bottomBarButtons
        )
    }
}

val bottomBarButtons = listOf(
    TrackerBottomBarButton(
        label = "Home",
        icon = Icons.Filled.Home,
        isSelected = true,
        navRoute = Home
    ),
    TrackerBottomBarButton(
        label = "Add",
        icon = Icons.Filled.Add,
        isSelected = false,
        navRoute = AddFoodItem
    ),
    TrackerBottomBarButton(
        label = "History",
        icon = Icons.Filled.History,
        isSelected = false,
        navRoute = AddNewFoodItem
    )
)

data class TrackerBottomBarButton(
    val label: String,
    val icon: ImageVector,
    val isSelected: Boolean,
    val navRoute: Destinations
)