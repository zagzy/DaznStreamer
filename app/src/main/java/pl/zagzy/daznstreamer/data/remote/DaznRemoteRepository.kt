package pl.zagzy.daznstreamer.data.remote

import pl.zagzy.daznstreamer.data.model.EventApi
import pl.zagzy.daznstreamer.data.model.ScheduleApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DaznRemoteRepository {
    @GET("getEvents")
    suspend fun getEvents(): List<EventApi>

    @GET("getSchedule")
    suspend fun getSchedule(): List<ScheduleApi>

    companion object {
        fun getInstance(): DaznRemoteRepository = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DaznRemoteRepository::class.java)

        private const val BASE_URL = "https://us-central1-dazn-sandbox.cloudfunctions.net"
    }


}

