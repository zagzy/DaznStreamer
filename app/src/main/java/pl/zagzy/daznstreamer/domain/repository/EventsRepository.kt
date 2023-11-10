package pl.zagzy.daznstreamer.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pl.zagzy.daznstreamer.data.model.EventApi
import pl.zagzy.daznstreamer.data.remote.EventsRemoteRepository
import pl.zagzy.daznstreamer.di.IoDispatcher
import pl.zagzy.daznstreamer.domain.model.Event
import pl.zagzy.daznstreamer.domain.model.toDomain
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class EventsRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) {

    private val remoteRepository: EventsRemoteRepository by lazy {
        Retrofit.Builder()
            .baseUrl("https://us-central1-dazn-sandbox.cloudfunctions.net")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventsRemoteRepository::class.java)
    }

    fun getAllEvents(
    ): Flow<List<Event>> = flow {
        // would be nice to cache those remote requests in latter iterations
        emit(remoteRepository.getEvents().map(EventApi::toDomain).sortedBy { it.date })
    }.flowOn(dispatcher)
}
