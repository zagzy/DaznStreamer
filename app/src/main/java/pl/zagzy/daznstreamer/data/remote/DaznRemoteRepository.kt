package pl.zagzy.daznstreamer.data.remote

import pl.zagzy.daznstreamer.data.model.EventApi
import pl.zagzy.daznstreamer.data.model.ScheduleApi
import retrofit2.http.GET

interface DaznRemoteRepository {
    @GET("getEvents")
    suspend fun getEvents(): List<EventApi>

    @GET("getSchedule")
    suspend fun getSchedule(): List<ScheduleApi>
}

