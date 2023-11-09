package pl.zagzy.daznstreamer.events

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.zagzy.daznstreamer.domain.model.Event
import pl.zagzy.daznstreamer.domain.repository.EventsRepository
import javax.inject.Inject

@HiltViewModel
class EventsViewModelImpl @Inject constructor(
    eventsRepository: EventsRepository
) : EventsViewModel, ViewModel() {
    override val events: Flow<List<Event>> = eventsRepository.getAllEvents()
}


object EventsViewModelPreview : EventsViewModel, ViewModel() {
    override val events: Flow<List<Event>>
        get() = flowOf(listOf(Event(0, "title", "", "", "", "")))
}

interface EventsViewModel {
    val events: Flow<List<Event>>
}
