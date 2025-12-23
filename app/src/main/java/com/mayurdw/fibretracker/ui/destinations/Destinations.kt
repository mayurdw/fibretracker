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
object AddNewFoodItem : Destinations

@Serializable
data class AddAmountItem(
    val selectedFoodId: Int
) : Destinations

@Serializable
data class EditEntry(
    val selectedEntryId: Int
) : Destinations

@Serializable
object EditMenu : Destinations

@StringRes
fun getTitle(destinations: Destinations): Int {
    return when (destinations) {
        Home -> R.string.home
        AddFoodItem -> R.string.add_food
        AddNewFoodItem -> R.string.add_new_food
        EditMenu -> R.string.edit_menu
        is AddAmountItem -> R.string.add_amount
        is EditEntry -> R.string.edit_food_entry
    }
}

fun getDestination(routeName: String?): Destinations {
    return when (routeName) {
        Home.javaClass.canonicalName -> Home
        AddFoodItem.javaClass.canonicalName -> AddFoodItem
        AddNewFoodItem.javaClass.canonicalName -> AddNewFoodItem
        EditMenu.javaClass.canonicalName -> EditMenu
        else -> {
            if (routeName?.contains(AddAmountItem::class.java.simpleName) == true) {
                AddAmountItem(-1)
            } else {
                EditEntry(-1)
            }
        }
    }
}
