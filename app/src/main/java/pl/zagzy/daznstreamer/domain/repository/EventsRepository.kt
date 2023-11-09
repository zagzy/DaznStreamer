package pl.zagzy.daznstreamer.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.zagzy.daznstreamer.domain.model.Event
import javax.inject.Inject

class EventsRepository @Inject constructor(
//    private val scope: CoroutineScope,
) {

    fun getAllEvents(
    ): Flow<List<Event>> {
//        return emptyFlow()
        return flowOf(listOf(Event(0, "title", "", "", "", "")))
    }
}
