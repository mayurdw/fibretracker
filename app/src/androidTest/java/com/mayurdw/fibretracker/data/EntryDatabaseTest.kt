package com.mayurdw.fibretracker.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.mayurdw.fibretracker.data.database.EntryDao
import com.mayurdw.fibretracker.data.database.EntryDatabase
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

    private lateinit var db: EntryDatabase
    private lateinit var dao: EntryDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, EntryDatabase::class.java).build()
        dao = db.getEntryDao()
    }

    @After
    fun finish() {
        db.close()
    }

    @Test
    fun `test database created and empty`() = runTest {
        dao.getEntries(Long.MIN_VALUE, Long.MAX_VALUE).test {
            val list = awaitItem()
            assertTrue(list.isEmpty())
        }
    }

    @Test
    fun `test database adds a new object`() = runTest {
        dao.insertEntry(
            FoodEntryEntity(
                "test",
                1,
                1,
                12L
            )
        )

        dao.getEntries(Long.MIN_VALUE, Long.MAX_VALUE).test {
            val list = awaitItem()
            assertTrue(list.isNotEmpty())
        }
    }


    @Test
    fun `test correct ordered elements returned`() = runTest {
        repeat(5) {
            dao.insertEntry(
                FoodEntryEntity("$it", it, it, it.toLong())
            )
        }

        dao.getEntries(
            startTime = 3, endTime = 5
        ).test {
            val list = awaitItem()
            assertEquals(2, list.size)
            assertEquals(4, list[0].date)
            assertEquals(3, list[1].date)
        }
    }

    @Test
    fun `test current date returns correct values`() = runTest {
        repeat(5) {
            dao.insertEntry(
                FoodEntryEntity("$it", it, it, it.toLong())
            )
        }

        dao.getEntries(
            startTime = 2, endTime = 2
        ).test {
            val list = awaitItem()
            assertTrue(list.isNotEmpty())
            assertEquals(1, list.count())
            assertEquals(2, list[0].date)
        }
    }
}
