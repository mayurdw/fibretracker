package com.mayurdw.fibretracker.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mayurdw.fibretracker.model.entity.FoodEntryEntity

@Dao
interface EntryDao {
    @Query("SELECT * FROM entry WHERE dateTime LIKE :currentDate")
    fun getEntries(currentDate: Long): List<FoodEntryEntity>

    @Upsert
    fun insertEntry(entryEntity: FoodEntryEntity)
}
