package pl.zagzy.daznstreamer.utils.extensions

import pl.zagzy.daznstreamer.domain.model.Event
import pl.zagzy.daznstreamer.utils.getRelativeDateFormatted

fun Event.setDateRelative(currentTimeMs: Long): Event =
    copy(date = getRelativeDateFormatted(date, currentTimeMs))
