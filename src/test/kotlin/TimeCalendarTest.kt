
import com.lucciola.calendar.Day
import com.lucciola.calendar.TimeCalendar
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.File
import java.util.*

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
        this.jsonFile.absoluteFile.bufferedWriter().write("")
    }

    @Test
    fun testAddingAndReadingDayList() {
        this.timeCalendar.addDay(Date(), 0, 0, 10)
        this.timeCalendar.addDay(Date(), 0, 0, 50)
        this.timeCalendar.makeJson()
        println(this.timeCalendar.json)
        this.timeCalendar.writeFile()
        val jsonData: List<Day> = this.timeCalendar.jsonData
        this.timeCalendar.readFile()
        val jsonData2: List<Day> = this.timeCalendar.jsonData
        assertEquals(jsonData, jsonData2)
    }

    @Test
    fun testSearchDate() {
        val date = Date()
        this.timeCalendar.addDay(date, 0, 0, 10)
        assertEquals("00:00:10", this.timeCalendar.searchDate(date))
    }
}
