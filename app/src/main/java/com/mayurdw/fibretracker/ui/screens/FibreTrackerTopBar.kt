package com.mayurdw.fibretracker.ui.screens

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mayurdw.fibretracker.R
import com.mayurdw.fibretracker.ui.destinations.AddAmountItem
import com.mayurdw.fibretracker.ui.destinations.AddFoodItem
import com.mayurdw.fibretracker.ui.destinations.AddNewFoodItem
import com.mayurdw.fibretracker.ui.destinations.Destinations
import com.mayurdw.fibretracker.ui.destinations.EditEntry
import com.mayurdw.fibretracker.ui.destinations.Home
import com.mayurdw.fibretracker.ui.destinations.getTitle
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FibreTrackerTopBar(
    currentDestination: Destinations = Home,
    onBackPressed: () -> Unit = {},
    onAddPressed: () -> Unit = {}
) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        title = {
            Text(
                stringResource(
                    getTitle(currentDestination)
                )
            )
        },
        navigationIcon = {
            if (Home != currentDestination) {
                IconButton(onClick = onBackPressed) {
                    Icon(
                        painterResource(R.drawable.previous),
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            if (Home == currentDestination) {
                IconButton(onClick = onAddPressed) {
                    Icon(
                        painterResource(R.drawable.edit),
                        contentDescription = null
                    )
                }
            }
        }
    )
}

@Composable
@PreviewLightDark
@PreviewDynamicColors
private fun PreviewFibreTrackerTopBar(
    @PreviewParameter(TopBarPreviewProvider::class) destination: Destinations
) {
    FibreTrackerTheme {
        FibreTrackerTopBar(destination) { }
    }
}

internal class TopBarPreviewProvider : PreviewParameterProvider<Destinations> {
    override val values: Sequence<Destinations> = sequenceOf(
        Home,
        AddFoodItem,
        AddNewFoodItem,
        AddAmountItem(-1),
        EditEntry(-1)
    )
}
