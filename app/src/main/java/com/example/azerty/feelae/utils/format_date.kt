package com.example.azerty.feelae.utils

import java.text.SimpleDateFormat

fun format(dateString: String?): String? {
    val outputFormat = SimpleDateFormat("dd/MM/yyyy")
    val inputFormat = SimpleDateFormat("dd/MM/yyyy")

    val date = inputFormat.parse(dateString)
    return outputFormat.format(date)

}