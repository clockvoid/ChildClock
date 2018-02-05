
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.animation.Timeline
import javafx.animation.KeyFrame
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.util.Duration
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class ChildClockController : Initializable {
    @FXML lateinit var label1: Label
    @FXML lateinit var button1: Button
    private var hour: Int = 0
    private var min: Int = 0
    private var sec: Int = 0
    private val timer = Timeline(KeyFrame(Duration.millis(1000.0), object : EventHandler<ActionEvent> {
        override fun handle(event: ActionEvent) {
            updateTime()
        }
    }))
    private var movingFlag: Boolean = true

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.timer.cycleCount = Timeline.INDEFINITE
        this.timer.play()
        this.button1.onAction = EventHandler<ActionEvent> {
            this.button1OnAction()
        }
    }

    fun updateTime() {
        if (this.sec == 59 && this.min == 59) {
            this.hour++
            this.sec = 0
            this.min = 0
        } else if (this.sec == 59) {
            this.min++
            this.sec = 0
        } else  {
            this.sec++
        }
        this.label1.text = "%02d:%02d:%02d".format(this.hour, this.min, this.sec)
    }

    private fun button1OnAction() {
        if (this.movingFlag) {
            this.timer.stop()
            this.button1.text = "START"
            this.movingFlag = false
        } else {
            this.timer.play()
            this.button1.text = "STOP"
            this.movingFlag = true
        }
    }
}
