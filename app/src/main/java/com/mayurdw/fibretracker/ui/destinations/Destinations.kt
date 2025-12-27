package com.mayurdw.fibretracker.ui.destinations

import androidx.annotation.StringRes
import com.mayurdw.fibretracker.R
import kotlinx.serialization.Serializable

sealed interface Destinations

@Serializable
object Home : Destinations

@Serializable
object SelectFoodToEdit : Destinations

@Serializable
data class EnterEditedFood(
    val selectedFoodId: Int
) : Destinations

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
        is Home -> R.string.home
        is AddFoodItem -> R.string.add_food
        is AddNewFoodItem -> R.string.add_new_food
        is EditMenu -> R.string.edit_menu
        is SelectFoodToEdit -> R.string.select_food_to_edit
        is AddAmountItem -> R.string.add_amount
        is EditEntry -> R.string.edit_food_entry
        is EnterEditedFood -> R.string.edit_existing_food
    }
}

fun getDestination(routeName: String?): Destinations {
    routeName?.let {
        val screens: List<Destinations> = listOf(
            Home,
            AddNewFoodItem,
            AddFoodItem,
            EditMenu,
            AddAmountItem(-1),
            EditEntry(-1),
            SelectFoodToEdit,
            EnterEditedFood(-1)
        )

        for (screen in screens) {
            if (it.contains(screen::class.java.simpleName, ignoreCase = true)) {
                return screen
            }
        }
    }

    throw IllegalStateException("Screen Name not found $routeName")
}
