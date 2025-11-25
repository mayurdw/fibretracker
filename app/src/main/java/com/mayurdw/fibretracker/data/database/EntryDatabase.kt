package com.mayurdw.fibretracker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity

@Database(entities = [FoodEntryEntity::class], version = 1, exportSchema = false)
abstract class EntryDatabase : RoomDatabase() {
    abstract fun getEntryDao(): EntryDao
}
