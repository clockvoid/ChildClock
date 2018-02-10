
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class TimeCalendarTest {
    lateinit var jsonFile: File
    private lateinit var timeCalendar: TimeCalendar

    @Before
    fun setup() {
        jsonFile = File("src/test/resources/calendar.json")
        timeCalendar = TimeCalendar(this.jsonFile)
    }

    @Test
    fun testWriteFileCorrectly() {
        this.jsonFile.absoluteFile.bufferedWriter(Charsets.UTF_8).use({ out -> out.write("test") })
        assertEquals("test", this.jsonFile.readText(Charsets.UTF_8))
    }
}
