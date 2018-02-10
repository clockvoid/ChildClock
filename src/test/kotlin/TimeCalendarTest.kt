
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
        val day1 = Day(Date().toString(), 100.toString())
        val day2 = Day(Date().toString(), 10000.toString())
        this.timeCalendar.addDay(day1)
        this.timeCalendar.addDay(day2)
        this.timeCalendar.makeJson()
        println(this.timeCalendar.json)
        this.timeCalendar.writeFile()
        val jsonData: List<Day> = this.timeCalendar.jsonData
        this.timeCalendar.readFile()
        val jsonData2: List<Day> = this.timeCalendar.jsonData
        assertEquals(jsonData, jsonData2)
    }
}
