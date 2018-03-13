import com.lucciola.Settings
import junit.framework.TestCase.assertEquals
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
        this.settings.readFile()
        assertEquals(false, this.settings.isEnableReset())
    }
}