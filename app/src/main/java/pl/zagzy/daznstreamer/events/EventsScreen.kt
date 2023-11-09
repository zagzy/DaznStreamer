package pl.zagzy.daznstreamer.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import pl.zagzy.daznstreamer.domain.model.AbstractEvent
import pl.zagzy.daznstreamer.domain.model.Event
import pl.zagzy.daznstreamer.domain.model.LoadingPlaceholderEvent


@Composable
fun EventsScreen(vm: EventsViewModel = hiltViewModel<EventsViewModelImpl>()) {

    val events: List<AbstractEvent> by vm.events.collectAsState(listOf(LoadingPlaceholderEvent))

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(horizontal = 2.dp, vertical = 0.dp)
        ) {
            items(items = events) { event ->
                when (event) {
                    is Event -> EventRow(event = event)
                    is LoadingPlaceholderEvent -> SpinnerRow()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    EventsScreen(vm = EventsViewModelPreview)
}

