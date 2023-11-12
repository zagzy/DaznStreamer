package pl.zagzy.daznstreamer.domain.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import pl.zagzy.daznstreamer.data.model.ScheduleApi
import pl.zagzy.daznstreamer.data.remote.DaznRemoteRepository
import pl.zagzy.daznstreamer.domain.model.Schedule
import pl.zagzy.daznstreamer.domain.model.toDomain
import pl.zagzy.daznstreamer.utils.DateTimeFormatter
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.seconds

@Singleton
class ScheduleRepository @Inject constructor(
    private val scope: CoroutineScope,
    private val dateTimeFormatter: DateTimeFormatter,
    private val remoteRepository: DaznRemoteRepository,
) {

    private val allScheduleSharedFlow = MutableSharedFlow<List<Schedule>>(replay = 1)
    val allSchedule: Flow<List<Schedule>> = allScheduleSharedFlow

    init {
        plantRefreshAllSchedule()
    }

    private fun plantRefreshAllSchedule() {
        scope.launch {
            while (currentCoroutineContext().isActive) {
                allScheduleSharedFlow.emit(remoteRepository.getSchedule()
                    .sortedBy(ScheduleApi::dateIso8601)
                    .map { it.toDomain(dateTimeFormatter) })
                delay(30.seconds)
            }
        }
    }
}
