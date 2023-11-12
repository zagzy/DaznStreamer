package pl.zagzy.daznstreamer.domain.model

import pl.zagzy.daznstreamer.data.model.ScheduleApi
import pl.zagzy.daznstreamer.utils.DateTimeFormatter

data class Schedule(
    val id: Int,
    val title: String,
    val subtitle: String,
    val dateRelative: String,
    val imageUrl: String,
) : java.io.Serializable, AbstractSchedule

sealed interface AbstractSchedule

fun ScheduleApi.toDomain(dateTimeFormatter: DateTimeFormatter) = Schedule(
    id = id,
    title = title,
    subtitle = subtitle,
    dateRelative = dateTimeFormatter.getDateRelative(dateIso8601),
    imageUrl = imageUrl,
)
