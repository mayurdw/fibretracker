package com.mayurdw.fibretracker.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class FoodEntity(
    @ColumnInfo("name") val name: String,
    @ColumnInfo("single_serving_size") val singleServingSizeInGm: Int,
    @ColumnInfo("fibre_per_serving") val fibreQuantityPerServingInMG: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
