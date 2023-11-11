package pl.zagzy.daznstreamer

interface Timer {
    val currentTimeMs: Long
}

class AndroidTimer : Timer {
    override val currentTimeMs: Long
        get() = System.currentTimeMillis()
}
