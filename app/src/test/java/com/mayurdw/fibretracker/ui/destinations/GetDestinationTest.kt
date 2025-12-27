package com.mayurdw.fibretracker.ui.destinations

import org.junit.Assert.assertEquals
import org.junit.Test

class GetDestinationTest {

    @Test(expected = IllegalStateException::class)
    fun `Unknown Name Returns Default`() {
        getDestination("")
    }

    @Test
    fun `Home Returns Home`() {
        assertEquals(Home, getDestination("home"))
    }

    @Test
    fun `Add New Food Returns Add New Food`() {
        assertEquals(AddNewFoodItem, getDestination(AddNewFoodItem::class.java.simpleName))
    }

    @Test
    fun `Add Amount Item Returns Add New Item`() {
        assertEquals(AddAmountItem(-1), getDestination("addamountitem"))
    }

    @Test
    fun `Select Food To Edit`() {
        assertEquals(SelectFoodToEdit, getDestination("selectfoodtoedit"))
    }

    @Test
    fun `Enter Edited Food`() {
        assertEquals(EnterEditedFood(-1), getDestination("enterEditedFood"))
    }

    @Test
    fun `Add Food Item`() {
        assertEquals(AddFoodItem, getDestination("AddFoodItem"))
    }

    @Test
    fun `Edit Entry`() {
        assertEquals(EditEntry(-1), getDestination("EditEntry"))
    }

    @Test
    fun `Edit Menu`() {
        assertEquals(EditMenu, getDestination("EditMenu"))
    }
}
