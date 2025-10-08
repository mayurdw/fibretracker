package com.mayurdw.fibretracker

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mayurdw.fibretracker.data.FoodDao
import com.mayurdw.fibretracker.data.FoodDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class FoodDatabaseTest {
    private lateinit var dao: FoodDao
    private lateinit var db: FoodDatabase

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, FoodDatabase::class.java).build()
    }

    @After
    @Throws(IOException::class)
    fun finish() {
        db.close()
    }

    @Test
    fun `setup empty database`() = runTest {

    }
}
