package com.mayurdw.fibretracker.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDate

@Entity(tableName = "entry")
data class FoodEntryEntity(
    @ColumnInfo("foodId") val foodId: Int,
    @ColumnInfo("serving") val foodServingInGms: Int,
    @ColumnInfo("date") val date: LocalDate
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
