package com.example.util

import java.text.NumberFormat
import java.util.Locale

private val indianLocale: Locale = Locale.Builder().setLanguage("en").setRegion("IN").build()

fun formatInr(amount: Int): String {
    val formatter = NumberFormat.getNumberInstance(indianLocale)
    return "₹${formatter.format(amount)}"
}

fun formatInr(amount: Long): String {
    val formatter = NumberFormat.getNumberInstance(indianLocale)
    return "₹${formatter.format(amount)}"
}
