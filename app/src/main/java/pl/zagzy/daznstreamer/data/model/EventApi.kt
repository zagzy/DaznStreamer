package pl.zagzy.daznstreamer.data.model

data class EventApi(
    val id: Int,
    val title: String,
    val subtitle: String,
    /* Iso8601 */
    val date: String,
    val imageUrl: String,
    val videoUrl: String,
)
