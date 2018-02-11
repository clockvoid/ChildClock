package com.lucciola.calendar

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Modality
import javafx.stage.Stage

class CalendarView(private val primaryStage: Stage, private val timeCalendar: TimeCalendar) {
    private val stage = Stage()

    init {
        this.stage.title = "Calendar"
        this.stage.initModality(Modality.APPLICATION_MODAL)
        this.stage.initOwner(this.primaryStage)
        this.stage.icons.add(Image("web_hi_res_512.png"))
        this.stage.scene = Scene(FXMLLoader.load<Parent>(this.javaClass.classLoader.getResource("calendar.fxml")))
        this.stage.show()
    }
}