package com.mayurdw.fibretracker.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayurdw.fibretracker.model.entity.Food

@Database(entities = [Food::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDao
}
