
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class ChildClockController : Initializable {
    @FXML lateinit var label1: Label
    @FXML lateinit var button1: Button
    private lateinit var timerModel: TimerModel

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.button1.isMnemonicParsing = true
        this.button1.onAction = EventHandler<ActionEvent> {
            this.button1.isMnemonicParsing = false
            this.timerModel.onStartOrStop()
            this.button1.isMnemonicParsing = true
        }
        this.timerModel = TimerModel(this)
    }

    fun setTimeText(sec: Int, min: Int, hour: Int) {
        this.label1.text = "%02d:%02d:%02d".format(hour, min, sec)
    }

    fun setButtonText(text: String) {
        this.button1.text = text
    }
}
