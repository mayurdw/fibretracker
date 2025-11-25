package com.mayurdw.fibretracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayurdw.fibretracker.model.entity.FoodEntity

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun getFoodDao(): FoodDao
}
