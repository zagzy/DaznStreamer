package pl.zagzy.daznstreamer.presentation.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import pl.zagzy.daznstreamer.domain.model.AbstractSchedule
import pl.zagzy.daznstreamer.domain.model.LoadingPlaceholder
import pl.zagzy.daznstreamer.domain.model.Schedule
import pl.zagzy.daznstreamer.presentation.components.SpinnerRow

@Composable
fun ScheduleScreen(vm: ScheduleViewModel = hiltViewModel<ScheduleViewModelImpl>()) {

    val schedule: List<AbstractSchedule> by vm.schedule.collectAsStateWithLifecycle(
        listOf(LoadingPlaceholder)
    )

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
            items(items = schedule) { schedule ->
                when (schedule) {
                    is Schedule -> ScheduleRow(event = schedule)
                    is LoadingPlaceholder -> SpinnerRow()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    ScheduleScreen(vm = ScheduleViewModelPreview)
}
