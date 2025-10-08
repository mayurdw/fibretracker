package com.mayurdw.fibretracker.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Food(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("display_name") val displayName: String,
    @ColumnInfo("single_serving_size") val singleServingSizeInGm: String,
    @ColumnInfo("fibre_per_serving") val fibreQuantityPerServingInMG: String
)
