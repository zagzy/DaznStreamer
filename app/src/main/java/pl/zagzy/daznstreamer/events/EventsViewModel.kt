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
        get() = flowOf(
            listOf(
                Event(
                    0,
                    "title",
                    "subtitle",
                    "date",
                    "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20",
                    "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/promo.mp4?alt=media"
                )
            )
        )
}

interface EventsViewModel {
    val events: Flow<List<Event>>
}
