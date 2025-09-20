package com.mayurdw.fibretracker.ui.destinations

import kotlinx.serialization.Serializable

interface Destinations {
    val title: String
}

@Serializable
object Home : Destinations {
    override val title: String = "Fibre Tracker"
}

@Serializable
object AddFoodItem : Destinations {
    override val title: String = "Add Food"
}

@Serializable
data class AddAmountItem(val foodItem: String) : Destinations {
    override val title: String = "Add Quantity"
}
