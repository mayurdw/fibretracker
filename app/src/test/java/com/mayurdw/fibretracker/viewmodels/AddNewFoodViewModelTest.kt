package com.mayurdw.fibretracker.viewmodels

import com.mayurdw.fibretracker.data.usecase.IAddFoodUseCase
import com.mayurdw.fibretracker.data.usecase.IGetFoodUseCase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class AddNewFoodViewModelTest {
    @MockK
    lateinit var getFoodUseCase: IGetFoodUseCase

    @MockK
    lateinit var addFoodUseCase: IAddFoodUseCase

    lateinit var viewModel: AddNewFoodViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)

        viewModel = AddNewFoodViewModel(
            getFoodUseCase = getFoodUseCase,
            addFoodUseCase = addFoodUseCase
        )
    }

    @Test
    fun checkIfDependenciesAreNotNull() {
        assertNotNull(getFoodUseCase)
        assertNotNull(addFoodUseCase)
        assertTrue(this::addFoodUseCase.isInitialized)
        assertTrue(this::getFoodUseCase.isInitialized)
    }

    @Test
    fun checkIfValidWhenValid() {
        assertTrue(
            viewModel.isValid(
                foodName = "Name",
                foodServing = "24",
                fibrePerServing = "0.2"
            )
        )
    }

    @Test
    fun checkIfValidWhenBlank() {
        assertFalse(
            viewModel.isValid(
                foodName = "Name",
                foodServing = "  ",
                fibrePerServing = "0.2"
            )
        )
    }

    @Test
    fun checkIfValidWhenInvalid() {
        assertFalse(
            viewModel.isValid(
                foodName = "",
                foodServing = "",
                fibrePerServing = "0.2"
            )
        )
    }

    @Test
    fun checkIfUpdatedWhenNotUpdated() {
        val entity = FoodEntity(name = "Test", 1, 1_000_000)
        assertFalse(
            viewModel.isUpdated(
                data = entity,
                foodName = entity.name,
                foodServing = entity.singleServingSizeInGm.toString(),
                fibrePerServing = entity.fibrePerGram.toString()
            )
        )
    }

    @Test
    fun checkIfUpdatedWhenUpdated() {
        val entity = FoodEntity(name = "Test", 1, 1_000_000)
        assertTrue(
            viewModel.isUpdated(
                data = entity,
                foodName = entity.name,
                foodServing = entity.singleServingSizeInGm.toString(),
                fibrePerServing = "2"
            )
        )
        assertTrue(
            viewModel.isUpdated(
                data = entity,
                foodName = entity.name,
                foodServing = entity.singleServingSizeInGm.toString(),
                fibrePerServing = "2.9"
            )
        )
    }

    @Test(expected = NumberFormatException::class)
    fun checkIfUpdatedWhenInvalid() {
        val entity = FoodEntity(name = "Test", 1, 1_000_000)


        viewModel.isUpdated(
            data = entity,
            foodName = entity.name,
            foodServing = "Test",
            fibrePerServing = entity.fibrePerGram.toString()
        )

    }

    @Test
    fun checkIfUpdatedWhenBlank() {
        val entity = FoodEntity(name = "Test", 1, 1_000_000)

        assertTrue(
            viewModel.isUpdated(
                data = entity,
                foodName = entity.name,
                foodServing = " ",
                fibrePerServing = entity.fibrePerGram.toString()
            )
        )

        assertTrue(
            viewModel.isUpdated(
                data = entity,
                foodName = entity.name,
                foodServing = entity.singleServingSizeInGm.toString(),
                fibrePerServing = " "
            )
        )
    }
}
