package pl.zagzy.daznstreamer.utils.extensions

import pl.zagzy.daznstreamer.domain.model.Schedule
import pl.zagzy.daznstreamer.utils.getRelativeDateFormatted

fun Schedule.setDateRelative(currentTimeMs: Long): Schedule =
    copy(date = getRelativeDateFormatted(date, currentTimeMs))
