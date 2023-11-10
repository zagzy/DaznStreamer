package pl.zagzy.daznstreamer.domain.model

import pl.zagzy.daznstreamer.data.model.EventApi

data class Event(
    val id: Int,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String,
) : java.io.Serializable, AbstractEvent

data object LoadingPlaceholderEvent : AbstractEvent
sealed interface AbstractEvent


fun EventApi.toDomain() = Event(
    id = id,
    title = title,
    subtitle = subtitle,
    date = date,
    imageUrl = imageUrl,
    videoUrl = videoUrl
)
