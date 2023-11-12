package pl.zagzy.daznstreamer.domain.model

import pl.zagzy.daznstreamer.data.model.EventApi
import pl.zagzy.daznstreamer.utils.DateTimeFormatter

data class Event(
    val id: Int,
    val title: String,
    val subtitle: String,
    val dateRelative: String,
    val imageUrl: String,
    val videoUrl: String,
) : java.io.Serializable, AbstractEvent

sealed interface AbstractEvent


fun EventApi.toDomain(dateTimeFormatter: DateTimeFormatter) = Event(
    id = id,
    title = title,
    subtitle = subtitle,
    dateRelative = dateTimeFormatter.getDateRelative(dateIso8601),
    imageUrl = imageUrl,
    videoUrl = videoUrl
)
