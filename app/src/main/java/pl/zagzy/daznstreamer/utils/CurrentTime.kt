package pl.zagzy.daznstreamer.utils

interface CurrentTime {
    val currentTimeMs: Long
}

class AndroidTime : CurrentTime {
    override val currentTimeMs: Long
        get() = System.currentTimeMillis()
}
