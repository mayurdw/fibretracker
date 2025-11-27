package com.mayurdw.fibretracker.model.domain

import kotlinx.datetime.LocalDate
import java.math.BigDecimal

data class EntryData(
    val id: Int,
    val date: LocalDate,
    val name: String,
    val servingInGms: Int,
    val fibrePerMicroGrams: Int
) {
    val fibreConsumedInGms: BigDecimal
        get() = BigDecimal.valueOf(servingInGms * fibrePerMicroGrams / 1_000_000.00)
}
