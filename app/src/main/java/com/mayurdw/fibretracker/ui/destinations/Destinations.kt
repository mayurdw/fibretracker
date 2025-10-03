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
fun getTitle(routeName: String?): Int {
    return when (routeName) {
        Home.javaClass.canonicalName -> R.string.home
        AddFoodItem.javaClass.canonicalName -> R.string.add_food
        AddAmountItem.javaClass.canonicalName -> R.string.add_amount
        else -> R.string.unknown
    }
}
