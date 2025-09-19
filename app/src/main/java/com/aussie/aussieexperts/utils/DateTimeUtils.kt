package com.aussie.aussieexperts.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

object DateTimeUtils{

    fun formatTimestamp(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp

        val minutes = TimeUnit.MILLISECONDS.toMinutes(diff)
        val hours = TimeUnit.MILLISECONDS.toHours(diff)
        val days = TimeUnit.MILLISECONDS.toDays(diff)

        val date = Date(timestamp)
        val cal = Calendar.getInstance().apply { timeInMillis = timestamp }
        val todayCal = Calendar.getInstance()

        return when {
            minutes < 1 -> "now"
            minutes < 60 -> "$minutes min ago"
            hours < 24 -> "$hours hr ago"
            days == 1L -> "yesterday"
            days < 7 -> { // within a week → show weekday
                val weekdayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
                weekdayFormat.format(date)
            }
            todayCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR) -> {
                // same year → show day + month
                val day = cal.get(Calendar.DAY_OF_MONTH)
                val suffix = getDayOfMonthSuffix(day)
                val monthFormat = SimpleDateFormat("MMM", Locale.getDefault())
                "$day$suffix ${monthFormat.format(date)}"
            }
            else -> { // different year → full date
                val dateFormat = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())
                dateFormat.format(date)
            }
        }
    }

    // Helper → add "st", "nd", "rd", "th"
    private fun getDayOfMonthSuffix(n: Int): String {
        if (n in 11..13) return "th"
        return when (n % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }

}