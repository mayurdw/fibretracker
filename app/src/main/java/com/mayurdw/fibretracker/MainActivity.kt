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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.mayurdw.fibretracker.data.CommonFoods
import com.mayurdw.fibretracker.ui.destinations.AddAmountItem
import com.mayurdw.fibretracker.ui.destinations.AddFoodItem
import com.mayurdw.fibretracker.ui.destinations.Home
import com.mayurdw.fibretracker.ui.screens.AddEntryView
import com.mayurdw.fibretracker.ui.screens.AddFoodItemList
import com.mayurdw.fibretracker.ui.screens.HomeScreen
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val currentDestinationRoute =
                navController.currentBackStackEntryAsState().value?.destination?.route

            FibreTrackerTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(),
                            title = {
                                Text(currentDestinationRoute ?: "")
                            }
                        )
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
                            HomeScreen {
                                navController.navigate(route = AddFoodItem)
                            }
                        }
                        composable<AddFoodItem> {
                            AddFoodItemList(foodItems = CommonFoods) { foodItem ->
                                navController.navigate(
                                    route = AddAmountItem(foodItem.foodName)
                                )
                            }
                        }
                        composable<AddAmountItem> { backStackEntry ->
                            val foodName: AddAmountItem = backStackEntry.toRoute()
                            AddEntryView(selectedFoodItem = CommonFoods.find {
                                foodName.foodItem == it.foodName
                            } ?: CommonFoods[0])
                        }
                    }
                }
            }
        }
    }
}
