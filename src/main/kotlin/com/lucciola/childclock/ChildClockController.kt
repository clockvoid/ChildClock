package com.lucciola.childclock
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.control.MenuItem
import javafx.scene.input.KeyCombination
import javafx.stage.Stage
import java.net.URL
import java.util.*

open class ChildClockController : Initializable {
    @FXML lateinit var timeLabel: Label
    @FXML lateinit var startStopButton: Button
    @FXML lateinit var resetButton: Button
    @FXML lateinit var exit: MenuItem
    @FXML lateinit var calendar: MenuItem
    private lateinit var timerModel: TimerModel

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.startStopButton.isMnemonicParsing = true
        this.startStopButton.onAction = EventHandler<ActionEvent> {
            this.startStopButton.isMnemonicParsing = false
            this.timerModel.onStartOrStop()
            this.startStopButton.isMnemonicParsing = true
        }
        this.resetButton.isMnemonicParsing = true
        this.resetButton.onAction = EventHandler<ActionEvent>{
            this.resetButton.isMnemonicParsing = false
            this.timerModel.reset()
            this.resetButton.isMnemonicParsing = true
        }
        this.exit.accelerator = KeyCombination.valueOf("Shortcut+c")
        this.exit.onAction = EventHandler<ActionEvent> {
            Platform.exit()
        }
        this.timerModel = TimerModel(this)
        this.calendar.onAction = EventHandler {
            val primaryStage = this.timeLabel.scene.window as Stage
            this.timerModel.createCalendar(primaryStage)
        }
    }

    open fun setTimeText(sec: Int, min: Int, hour: Int) {
        this.timeLabel.text = "%02d:%02d:%02d".format(hour, min, sec)
    }

    open fun setButtonText(text: String) {
        this.startStopButton.text = text
    }
}
