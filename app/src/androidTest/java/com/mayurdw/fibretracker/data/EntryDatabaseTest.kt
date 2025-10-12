package com.mayurdw.fibretracker.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
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
        dao.getAllFlow().test {
            val list = awaitItem()
            assertTrue(list.isEmpty())
        }
    }

}
