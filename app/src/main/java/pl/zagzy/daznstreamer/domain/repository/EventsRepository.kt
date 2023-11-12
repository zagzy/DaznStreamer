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
import pl.zagzy.daznstreamer.utils.DateTimeFormatter
import javax.inject.Inject

class EventsRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    dateTimeFormatter: DateTimeFormatter,
    private val remoteRepository: DaznRemoteRepository,
) {
    val allEvents: Flow<List<Event>> = flow {
        emit(remoteRepository.getEvents()
            .sortedBy(EventApi::dateIso8601)
            .map { it.toDomain(dateTimeFormatter) })
    }.flowOn(dispatcher)
}
