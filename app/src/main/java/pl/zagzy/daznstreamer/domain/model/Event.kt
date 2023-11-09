package pl.zagzy.daznstreamer.domain.model

data class Event(
    val id: Int,
    val title: String,
    val subtitle: String,
    val date: String,
    val imageUrl: String,
    val videoUrl: String,
) : AbstractEvent

data object LoadingPlaceholderEvent : AbstractEvent
sealed interface AbstractEvent
