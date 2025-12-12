package com.mayurdw.fibretracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mayurdw.fibretracker.data.helpers.LocalDateConverters
import com.mayurdw.fibretracker.model.entity.FoodEntity
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity

@Database(
    version = 1,
    entities = [FoodEntity::class, FoodEntryEntity::class],
    exportSchema = false
)
@TypeConverters(LocalDateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getAppDao(): AppDao

}
