package com.mayurdw.fibretracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mayurdw.fibretracker.data.CommonFoods
import com.mayurdw.fibretracker.ui.destinations.AddAmountItem
import com.mayurdw.fibretracker.ui.destinations.AddFoodItem
import com.mayurdw.fibretracker.ui.destinations.Destinations
import com.mayurdw.fibretracker.ui.destinations.Home
import com.mayurdw.fibretracker.ui.destinations.getDestination
import com.mayurdw.fibretracker.ui.destinations.getTitle
import com.mayurdw.fibretracker.ui.screens.AddEntryView
import com.mayurdw.fibretracker.ui.screens.AddFoodItemLayout
import com.mayurdw.fibretracker.ui.screens.AddFoodItemList
import com.mayurdw.fibretracker.ui.screens.HomeScreen
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme
import dagger.hilt.android.AndroidEntryPoint


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FibreTrackerTopBar(currentDestination: Destinations = Home, onBackPressed: () -> Unit = {}) {
    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.secondary,
            navigationIconContentColor = MaterialTheme.colorScheme.secondary
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
        }
    )
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentDestinationRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route
            val destination = getDestination(currentDestinationRoute)

            FibreTrackerTheme {
                Scaffold(
                    topBar = {
                        FibreTrackerTopBar(destination) {
                            navController.navigateUp()
                        }
                    },
                    floatingActionButton = {
                        if (Home == destination) {
                            FloatingActionButton(onClick = {
                                navController.navigate(AddFoodItem)
                            }) {
                                Icon(
                                    painterResource(R.drawable.add),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .windowInsetsPadding(WindowInsets.safeDrawing)
                        .fillMaxSize()
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Home,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable<Home> {
                            HomeScreen()
                        }
                        composable<AddFoodItem> {
                            AddFoodItemLayout {
                                navController.navigate(AddAmountItem)
                            }
                        }
                        composable<AddAmountItem> {
                            AddEntryView()
                        }
                    }
                }
            }
        }
    }
}
