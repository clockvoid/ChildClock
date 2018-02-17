package com.lucciola.calendar

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import java.net.URL
import java.util.*

class CalendarController : Initializable {
    @FXML lateinit var pulldown: ComboBox<String>
    @FXML lateinit var button: Button
    @FXML lateinit var timeLabel: Label
    lateinit var timeCalendar: TimeCalendar

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        this.button.onAction = EventHandler<ActionEvent> {
            this.timeLabel.text = this.pulldown.value
        }
    }

    fun setPullDownList(list: List<String>) {
        this.pulldown.items.setAll(list)
    }
}