package com.mayurdw.fibretracker.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @ColumnInfo("display_name") val displayName: String,
    @ColumnInfo("single_serving_size") val singleServingSizeInGm: String,
    @ColumnInfo("fibre_per_serving") val fibreQuantityPerServingInMG: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
