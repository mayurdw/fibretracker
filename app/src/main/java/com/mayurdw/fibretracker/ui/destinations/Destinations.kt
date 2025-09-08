package com.mayurdw.fibretracker.ui.destinations

import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object AddFoodItem

@Serializable
data class AddAmountItem(val foodItem: String)
