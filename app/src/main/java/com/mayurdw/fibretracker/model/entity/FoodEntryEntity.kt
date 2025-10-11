package com.mayurdw.fibretracker.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entry")
data class FoodEntryEntity(
    @ColumnInfo("foodName") val foodDisplayName: String,
    @ColumnInfo("serving") val foodServingInGms: String,
    @ColumnInfo("fibre") val fibreThisServingInMilliGms: String,
    @ColumnInfo("dateTime") val date: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
