package com.mayurdw.fibretracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity

@Database(entities = [FoodEntity::class, FoodEntryEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao

}
