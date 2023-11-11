package pl.zagzy.daznstreamer.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pl.zagzy.daznstreamer.data.model.EventApi
import pl.zagzy.daznstreamer.data.remote.DaznRemoteRepository
import pl.zagzy.daznstreamer.di.IoDispatcher
import pl.zagzy.daznstreamer.domain.model.Event
import pl.zagzy.daznstreamer.domain.model.toDomain
import javax.inject.Inject

class EventsRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val remoteRepository: DaznRemoteRepository,
) {
    val allEvents: Flow<List<Event>> = flow {
        emit(remoteRepository.getEvents().map(EventApi::toDomain).sortedBy { it.date })
    }.flowOn(dispatcher)
}
