package pl.zagzy.daznstreamer.schedule

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import pl.zagzy.daznstreamer.domain.model.Schedule
import pl.zagzy.daznstreamer.domain.repository.ScheduleRepository
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModelImpl @Inject constructor(
    scheduleRepository: ScheduleRepository
) : ScheduleViewModel, ViewModel() {
    override val schedule: Flow<List<Schedule>> = scheduleRepository.allSchedule
}

object ScheduleViewModelPreview : ScheduleViewModel, ViewModel() {
    override val schedule: Flow<List<Schedule>>
        get() = flowOf(
            listOf(
                Schedule(
                    0,
                    "title",
                    "subtitle",
                    "date",
                    "https://firebasestorage.googleapis.com/v0/b/dazn-recruitment/o/310176837169_image-header_pDach_1554579780000.jpeg?alt=media&token=1777d26b-d051-4b5f-87a8-7633d3d6dd20"
                )
            )
        )
}

interface ScheduleViewModel {
    val schedule: Flow<List<Schedule>>
}
