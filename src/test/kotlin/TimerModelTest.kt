import com.lucciola.childclock.ChildClockController
import com.lucciola.childclock.TimerModel
import junit.framework.Assert.assertEquals
import org.apache.commons.lang3.time.DateUtils
import org.junit.Before
import org.junit.Test
import java.io.File
import java.text.DateFormat
import java.util.*

class TimerModelTest {
    private lateinit var model: TimerModel
    private lateinit var date: Date
    private lateinit var calendar: File

    @Before
    fun before() {
        this.calendar = File(this::class.java.getResource("calendar.json").path)
        this.calendar.absoluteFile.bufferedWriter().use { } // init the file
        this.model = TimerModel(ChildClockControllerMock(), this.calendar.path)
        this.date = Date()
    }

    @Test
    fun recordDayDataTest() {
        val now = DateUtils.addDays(Date(), 1)
        this.model.recordDayData(now)
        val dateFormat: DateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.JAPAN)
        assertEquals("[{\"date\":\"${dateFormat.format(this.date)}\",\"time\":\"00:00:00\"}]", this.calendar.absoluteFile.bufferedReader().use { it.readText() })
    }
}

class ChildClockControllerMock : ChildClockController() {
    var buttonString: String = ""
        private set
    var timeString: String = ""
        private set

    override fun setTimeText(sec: Int, min: Int, hour: Int) {
        this.timeString = "%02d:%02d:%02d".format(hour, min, sec)
    }

    override fun setButtonText(text: String) {
        this.buttonString = text
    }
}