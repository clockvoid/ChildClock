import com.lucciola.Settings
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertArrayEquals
import org.junit.Before
import org.junit.Test
import java.io.File

class SettingsTest {
    lateinit var settings: Settings

    @Before
    fun before() {
        this.settings = Settings(File("src/test/resources/settings.json"))
    }

    @Test
    fun testIsEnableReset() {
        assertEquals(false, this.settings.isEnableReset())
    }

    @Test
    fun testEnableTest() {
        this.settings.enableReset()
        assertEquals(true, this.settings.isEnableReset())
    }

    @Test
    fun testReadWriteFile() {
        this.settings.writeFile()
        this.settings.readFile(false)
        assertEquals(false, this.settings.isEnableReset())
    }

    @Test
    fun testDefaultSettingsFile() {
        this.settings.readFile(true)
        assertEquals(false, this.settings.setting.enable_reset)
        assertEquals(true, this.settings.setting.alert)
        assertArrayEquals(arrayOf(1, 0, 0), this.settings.setting.alert_time)
        assertEquals("One Hour alert", this.settings.setting.alert_title)
        assertEquals("一時間経ってます！", this.settings.setting.alert_header)
        assertEquals("一時間が経過しました．そろそろやめましょう．", this.settings.setting.alert_content)
    }
}