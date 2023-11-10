package pl.zagzy.daznstreamer.domain.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import pl.zagzy.daznstreamer.data.model.ScheduleApi
import pl.zagzy.daznstreamer.data.remote.DaznRemoteRepository
import pl.zagzy.daznstreamer.di.IoDispatcher
import pl.zagzy.daznstreamer.domain.model.Schedule
import pl.zagzy.daznstreamer.domain.model.toDomain
import javax.inject.Inject

class ScheduleRepository @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val remoteRepository: DaznRemoteRepository,
) {

    fun getAllSchedule(
    ): Flow<List<Schedule>> = flow {
        // would be nice to cache those remote requests in latter iterations
        emit(remoteRepository.getSchedule().map(ScheduleApi::toDomain).sortedBy { it.date })
    }.flowOn(dispatcher)
}
