package com.mayurdw.fibretracker.ui.destinations

import kotlinx.serialization.Serializable

interface Destinations {}

@Serializable
object Home : Destinations

@Serializable
object AddFoodItem : Destinations

@Serializable
data class AddAmountItem(val foodItem: String) : Destinations
