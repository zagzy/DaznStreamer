package pl.zagzy.daznstreamer.events

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EventsViewModelImpl @Inject constructor(

) : EventsViewModel, ViewModel()

interface EventsViewModel {
}
