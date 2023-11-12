package pl.zagzy.daznstreamer.utils

import javax.inject.Inject

class CurrentTime @Inject constructor() {
    val currentTimeMs: Long
        get() = System.currentTimeMillis()
}
