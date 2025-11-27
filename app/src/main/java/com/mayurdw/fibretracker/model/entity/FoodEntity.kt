package com.mayurdw.fibretracker.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * This is currently common for both entity and domain.
 * If in the future, we require different sets then split this please
 * */
@Entity(tableName = "food")
data class FoodEntity(
    @ColumnInfo("name") val name: String,
    @ColumnInfo("single_serving_size") val singleServingSizeInGm: Int,
    @ColumnInfo("fibre_per_micro_gram") val fibrePerMicroGram: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
