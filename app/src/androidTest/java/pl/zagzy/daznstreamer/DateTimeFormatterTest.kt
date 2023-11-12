package pl.zagzy.daznstreamer

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import pl.zagzy.daznstreamer.utils.CurrentTime
import pl.zagzy.daznstreamer.utils.DateTimeFormatter

@RunWith(MockitoJUnitRunner::class)
class DateTimeFormatterTest {

    @Mock
    lateinit var currentTimeMock: CurrentTime

    private lateinit var dateTimeFormatter: DateTimeFormatter

    @Before
    fun setUp() {
        dateTimeFormatter = DateTimeFormatter(currentTimeMock)
        `when`(currentTimeMock.currentTimeMs).thenReturn(1699693200000 /* 11/11/2023 10:00:00 */)
    }

    @Test
    fun shouldFormatDateRespectively1() {
        val dateRelative = dateTimeFormatter.getDateRelative("2023-11-10T05:00:00.000Z")
        MatcherAssert.assertThat(dateRelative, `is`("Yesterday, 06:00"))
    }

    @Test
    fun shouldFormatDateRespectively2() {
        val dateRelative = dateTimeFormatter.getDateRelative("2023-11-12T05:00:00.000Z")
        MatcherAssert.assertThat(dateRelative, `is`("Today, 06:00"))
    }

    @Test
    fun shouldFormatDateRespectively3() {
        val dateRelative = dateTimeFormatter.getDateRelative("2023-11-13T05:00:00.000Z")
        MatcherAssert.assertThat(dateRelative, `is`("Tomorrow, 06:00"))
    }

    @Test
    fun shouldFormatDateRespectively4() {
        val dateRelative = dateTimeFormatter.getDateRelative("2023-11-14T05:00:00.000Z")
        MatcherAssert.assertThat(dateRelative, `is`("In two days"))
    }

    @Test
    fun shouldFormatDateRespectively5() {
        val dateRelative = dateTimeFormatter.getDateRelative("2023-11-15T05:00:00.000Z")
        MatcherAssert.assertThat(dateRelative, `is`("In three days"))
    }

    @Test
    fun shouldFormatDateRespectively6() {
        val dateRelative = dateTimeFormatter.getDateRelative("2023-11-16T05:00:00.000Z")
        MatcherAssert.assertThat(dateRelative, `is`("16.11.2023"))
    }
}
