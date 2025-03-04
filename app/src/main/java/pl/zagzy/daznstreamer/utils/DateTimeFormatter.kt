package pl.zagzy.daznstreamer.utils

import org.joda.time.DateTime
import org.joda.time.Days
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

open class DateTimeFormatter @Inject constructor(
    private val currentTime: CurrentTime
) {
    fun getDateRelative(dateIso8601: String): String =
        getFormattedDateRelativeToCurrent(
            currentDateMs = currentTime.currentTimeMs,
            specificDateMs = convertIso8601ToTimestamp(dateIso8601)
        )

    private fun getFormattedDateRelativeToCurrent(
        currentDateMs: Long,
        specificDateMs: Long
    ): String {
        val currentDate = DateTime(currentDateMs)
        val specificDate = DateTime(specificDateMs)

        return when (Days.daysBetween(currentDate, specificDate).days) {
            -1 -> "Yesterday, ${formattedTime(specificDate)}"
            0 -> "Today, ${formattedTime(specificDate)}"
            1 -> "Tomorrow, ${formattedTime(specificDate)}"
            2 -> "In two days"
            3 -> "In three days"
            else -> specificDate.toString("dd.MM.yyyy")
        }
    }

    private fun formattedTime(specificDate: DateTime): String = specificDate.toString("HH:mm")

    private fun convertIso8601ToTimestamp(iso8601date: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale("pl", "PL"))
        sdf.timeZone = TimeZone.getTimeZone("Europe/Warsaw")
        val date = sdf.parse(iso8601date)
        return date?.time ?: 0
    }
}
