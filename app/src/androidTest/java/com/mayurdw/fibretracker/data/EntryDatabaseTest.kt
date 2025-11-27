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
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
    fun `test database created and empty`() = runTest {
        dao.getEntryData(Long.MIN_VALUE, Long.MAX_VALUE).test {
            val list = awaitItem()
            assertTrue(list.isEmpty())
        }
    }

    @Test
    fun `test multimap between food and entry`() = runTest {
        val food = FoodEntity(
            name = "Food",
            singleServingSizeInGm = 10,
            fibrePerMicroGram = 2000
        ).apply { id = 1 }
        val entry = FoodEntryEntity(
            foodId = food.id,
            foodServingInGms = 5,
            date = 10
        )

        dao.insertFood(
            food
        )

        dao.insertEntry(
            entry
        )

        dao.getEntryData(0, 20).test {
            val list = awaitItem()

            assertEquals(1, list.size)
            assertEquals(entry.foodServingInGms, list[0].servingInGms)
            assertEquals(food.name, list[0].name)
        }
    }

    @Test
    fun `multiple entries of one food`() = runTest {
        val food = FoodEntity(
            name = "Test",
            singleServingSizeInGm = 10,
            fibrePerMicroGram = 2000
        ).apply { id = 1 }
        val entries = listOf(
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm,
                date = 10L
            ),
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm * 2,
                date = 4L
            )
        )

        entries.forEach { dao.insertEntry(it) }
        dao.insertFood(food)

        dao.getEntryData(5L, 12L).test {
            val list = awaitItem()

            assertEquals(1, list.size)
            assertEquals(entries[0].foodServingInGms, list[0].servingInGms)
        }
    }

    @Test
    fun `multiple entries of same date`() = runTest {
        val food = FoodEntity(
            name = "Test",
            singleServingSizeInGm = 10,
            fibrePerMicroGram = 2000
        ).apply { id = 1 }
        val entries = listOf(
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm,
                date = 10L
            ),
            FoodEntryEntity(
                foodId = food.id,
                foodServingInGms = food.singleServingSizeInGm * 2,
                date = 10L
            )
        )

        entries.forEach { dao.insertEntry(it) }
        dao.insertFood(food)

        dao.getEntryData(5L, 12L).test {
            val list = awaitItem()

            assertEquals(2, list.size)
            assertEquals(entries[0].foodServingInGms, list[0].servingInGms)
            assertEquals(entries[1].foodServingInGms, list[1].servingInGms)
        }
    }
}
