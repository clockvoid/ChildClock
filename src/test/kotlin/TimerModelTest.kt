import com.lucciola.childclock.ChildClockController
import com.lucciola.childclock.TimerModel
import org.apache.commons.lang3.time.DateUtils
import org.junit.Before
import org.junit.Test
import java.util.*

class TimerModelTest {
    private lateinit var model: TimerModel

    @Before
    fun before() {
        this.model = TimerModel(ChildClockControllerMock())
    }

    @Test
    fun updateTimeTest() {
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