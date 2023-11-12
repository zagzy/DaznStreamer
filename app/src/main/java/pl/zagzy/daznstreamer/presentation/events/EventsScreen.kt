package pl.zagzy.daznstreamer.presentation.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import pl.zagzy.daznstreamer.domain.model.AbstractEvent
import pl.zagzy.daznstreamer.domain.model.Event
import pl.zagzy.daznstreamer.domain.model.LoadingPlaceholder
import pl.zagzy.daznstreamer.presentation.components.ExoPlayerDialog
import pl.zagzy.daznstreamer.presentation.components.SpinnerRow


@Composable
fun EventsScreen(vm: EventsViewModel = hiltViewModel<EventsViewModelImpl>()) {

    val context = LocalContext.current
    var showPlaybackDialog by remember { mutableStateOf(false) }
    val exoPlayer = remember { ExoPlayer.Builder(context).build() }
    val events: List<AbstractEvent> by vm.events.collectAsState(listOf(LoadingPlaceholder))

    if (showPlaybackDialog) {
        ExoPlayerDialog(exoPlayer, onDismissRequest = { showPlaybackDialog = false })
    }

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
                    is Event -> EventRow(
                        event = event,
                        onEventSelected = { event ->
                            exoPlayer.playSingleUrl(event.videoUrl)
                            showPlaybackDialog = true
                        })

                    is LoadingPlaceholder -> SpinnerRow()
                }
            }
        }
    }
}

private fun ExoPlayer.playSingleUrl(url: String) {
    clearMediaItems()
    addMediaItem(MediaItem.fromUri(url))
    prepare()
    playWhenReady = true
}

@Preview(showBackground = true)
@Composable
fun EventsScreenPreview() {
    EventsScreen(vm = EventsViewModelPreview)
}
