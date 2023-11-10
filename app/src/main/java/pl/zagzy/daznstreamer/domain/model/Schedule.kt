package pl.zagzy.daznstreamer.domain.model

import pl.zagzy.daznstreamer.data.model.ScheduleApi

data class Schedule(
    val id: Int,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
) : java.io.Serializable, AbstractSchedule

sealed interface AbstractSchedule

fun ScheduleApi.toDomain() = Schedule(
    id = id,
    title = title,
    subtitle = subtitle,
    date = date,
    imageUrl = imageUrl,
)
