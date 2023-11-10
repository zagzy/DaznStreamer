package pl.zagzy.daznstreamer.data.remote

import pl.zagzy.daznstreamer.data.model.EventApi
import retrofit2.http.GET

interface EventsRemoteRepository {
    @GET("getEvents")
    suspend fun getEvents(): List<EventApi>
}

