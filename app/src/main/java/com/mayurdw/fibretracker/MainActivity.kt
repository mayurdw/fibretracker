package com.mayurdw.fibretracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mayurdw.fibretracker.data.CommonFoods
import com.mayurdw.fibretracker.ui.components.AddEntryView
import com.mayurdw.fibretracker.ui.components.AddFoodItemList
import com.mayurdw.fibretracker.ui.destinations.AddAmountItem
import com.mayurdw.fibretracker.ui.destinations.AddFoodItem
import com.mayurdw.fibretracker.ui.destinations.Home
import com.mayurdw.fibretracker.ui.theme.FibreTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            NavHost(navController, startDestination = Home) {
                composable<Home> { HomeScreen() }
                composable<AddFoodItem> { AddFoodItemList(foodItems = emptyList()) { } }
                composable<AddAmountItem> { AddEntryView(selectedFoodItem = CommonFoods[0]) }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    FibreTrackerTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Text("Hello World", modifier = Modifier.padding(innerPadding))
        }
    }
}
