package pl.zagzy.daznstreamer.utils

import org.joda.time.DateTime
import org.joda.time.Days
import pl.zagzy.daznstreamer.utils.extensions.EMPTY
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun getRelativeDateFormatted(dateFormatted: String, currentTimeMs: Long): String {
    val calendar: Calendar = Calendar.getInstance().apply { timeInMillis = currentTimeMs }

    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US).apply {
        timeZone = TimeZone.getTimeZone("UTC")
    }
    calendar.time = format.parse(dateFormatted) ?: Date()

    return getRelativeDateTimeString(startDate = calendar, today = DateTime())
}

private val DATE_FORMAT: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
private val TIME_FORMAT: SimpleDateFormat = SimpleDateFormat("',' HH:mm")
private fun getRelativeDateTimeString(startDate: Calendar?, today: DateTime): String {
    if (startDate == null) return String.EMPTY
    val startDate = DateTime(startDate.timeInMillis)
    val date: String = when (Days.daysBetween(
        today.withTimeAtStartOfDay(),
        startDate.withTimeAtStartOfDay()
    ).days) {
        -1 -> "Yesterday"
        0 -> "Today"
        1 -> "Tomorrow"
        2 -> "In two days"
        3 -> "In three days"
        else -> DATE_FORMAT.format(startDate.toDate())
    }
    val time: String = TIME_FORMAT.format(startDate.toDate())
    return date + time
}
