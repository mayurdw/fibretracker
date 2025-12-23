package com.mayurdw.fibretracker.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.mayurdw.fibretracker.data.database.AppDao
import com.mayurdw.fibretracker.data.database.AppDatabase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.todayIn
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
@RunWith(AndroidJUnit4::class)
class EntryDatabaseTest {

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    private lateinit var db: AppDatabase
    private lateinit var dao: AppDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.getAppDao()
    }

    @After
    fun finish() {
        db.close()
    }

    @Test
    fun testDatabaseIsEmpty() = runTest {
        val currentDate = getCurrentDate()
        dao.getEntryData(currentDate, currentDate).test {
            val list = awaitItem()
            assertTrue(list.isEmpty())
        }

        dao.checkIfEntryDataExists(currentDate, currentDate).test {
            assertFalse(awaitItem())
        }
    }

    @Test
    fun testMultimapBetweenFoodAndEntry() = runTest {
        val currentDate = getCurrentDate()
        val food = FoodEntity(
            name = "Food",
            singleServingSizeInGm = 10,
            fibrePerMicroGram = 2000
        ).apply { id = 1 }
        val entry = FoodEntryEntity(
            foodId = food.id,
            foodServingInGms = 5,
            date = currentDate
        )

        dao.insertFood(
            food
        )

        dao.upsertEntry(
            entry
        )

        dao.getEntryData(LocalDate.fromEpochDays(0), currentDate).test {
            val list = awaitItem()

            assertEquals(1, list.size)
            assertEquals(entry.foodServingInGms, list[0].servingInGms)
            assertEquals(food.name, list[0].name)
        }

        dao.checkIfEntryDataExists(currentDate, currentDate).test {
            assertTrue(awaitItem())
        }
    }

    @Test
    fun testMultipleEntriesOfSameFood() = runTest {
        val currentDate = getCurrentDate()
        val food = FoodEntity(
            name = "Test",
            singleServingSizeInGm = 10,
            fibrePerMicroGram = 2000
        ).apply { id = 1 }
        val entries = listOf(
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm,
                date = currentDate
            ),
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm * 2,
                date = currentDate.minus(1, DateTimeUnit.DAY)
            )
        )

        entries.forEach { dao.upsertEntry(it) }
        dao.insertFood(food)

        dao.getEntryData(currentDate, currentDate).test {
            val list = awaitItem()

            assertEquals(1, list.size)
            assertEquals(entries[0].foodServingInGms, list[0].servingInGms)
        }
    }

    @Test
    fun testMultipleEntriesOfSameDate() = runTest {
        val currentDate = getCurrentDate()
        val food = FoodEntity(
            name = "Test",
            singleServingSizeInGm = 10,
            fibrePerMicroGram = 2000
        ).apply { id = 1 }
        val entries = listOf(
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm,
                date = currentDate
            ),
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm * 2,
                date = currentDate
            )
        )

        entries.forEach { dao.upsertEntry(it) }
        dao.insertFood(food)

        dao.getEntryData(currentDate, currentDate).test {
            val list = awaitItem()

            assertEquals(2, list.size)
            assertEquals(entries[0].foodServingInGms, list[0].servingInGms)
            assertEquals(entries[1].foodServingInGms, list[1].servingInGms)
        }
    }


    fun testUpsertASingleEntry() = runTest {
        val currentDate = getCurrentDate()
        val food = FoodEntity(
            name = "Test",
            singleServingSizeInGm = 10,
            fibrePerMicroGram = 2000
        ).apply { id = 1 }
        val entry =
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm,
                date = currentDate
            ).apply { id = 1 }

        dao.upsertEntry(entry)
        dao.insertFood(food)


        dao.getEntryData(currentDate, currentDate).test {
            val list = awaitItem()

            assertEquals(1, list.size)
        }


        val newEntry = entry.copy(foodServingInGms = food.singleServingSizeInGm / 2)

        assertEquals(entry.id, newEntry.id)
        assertEquals(entry.foodServingInGms, newEntry.foodServingInGms * 2)
        dao.upsertEntry(newEntry)

        dao.getEntryData(currentDate, currentDate).test {
            val list = awaitItem()

            assertEquals(1, list.size)
            assertEquals(newEntry.foodServingInGms, list[0].servingInGms)
        }
    }

    @Test
    fun testGetSingleEntry() = runTest {
        val currentDate = getCurrentDate()
        val food = FoodEntity(
            name = "Test",
            singleServingSizeInGm = 10,
            fibrePerMicroGram = 2000
        ).apply { id = 1 }
        val entry =
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm,
                date = currentDate
            ).apply { id = 1 }

        dao.upsertEntry(entry)
        dao.insertFood(food)


        dao.getEntry(entry.id).test {
            val data = awaitItem()

            assertEquals(entry.id, data.id)
            assertEquals(entry.foodServingInGms, data.servingInGms)
            assertEquals(entry.date, data.date)
        }

    }

    inline fun getCurrentDate() = Clock.System.todayIn(TimeZone.currentSystemDefault())
}
