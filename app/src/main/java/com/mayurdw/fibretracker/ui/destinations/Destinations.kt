package com.mayurdw.fibretracker.ui.destinations

import androidx.annotation.StringRes
import com.mayurdw.fibretracker.R
import kotlinx.serialization.Serializable

sealed interface Destinations

@Serializable
object Home : Destinations

@Serializable
object AddFoodItem : Destinations

@Serializable
object AddAmountItem : Destinations

@StringRes
fun getTitle(destinations: Destinations): Int {
    return when (destinations) {
        Home -> R.string.home
        AddFoodItem -> R.string.add_food
        AddAmountItem -> R.string.add_amount
        else -> R.string.unknown
    }
}

fun getDestination(routeName: String?): Destinations {
    return when (routeName) {
        Home.javaClass.canonicalName -> Home
        AddFoodItem.javaClass.canonicalName -> AddFoodItem
        AddAmountItem.javaClass.canonicalName -> AddAmountItem
        else -> Home
    }
}
