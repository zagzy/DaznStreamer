package pl.zagzy.daznstreamer.data.model

import com.google.gson.annotations.SerializedName

data class ScheduleApi(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("date")
    val dateIso8601: String,

    @SerializedName("imageUrl")
    val imageUrl: String,
)
