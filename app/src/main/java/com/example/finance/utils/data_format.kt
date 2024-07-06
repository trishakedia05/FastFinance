package com.example.finance.utils

import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateFormatted(dateString: String): String {
    // Parse the input date string
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date = LocalDate.parse(dateString, inputFormatter)

    // Format the date as "12th November, 2024"
    val outputFormatter = DateTimeFormatter.ofPattern("d MMMM, yyyy")
    val formattedDate = date.format(outputFormatter)
    return formattedDate
}