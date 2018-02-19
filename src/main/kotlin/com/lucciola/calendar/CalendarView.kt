package com.lucciola.calendar

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Modality
import javafx.stage.Stage

class CalendarView(private val primaryStage: Stage, private val timeCalendar: TimeCalendar) {
    private val stage = Stage()
    private val fxmlLoader = FXMLLoader(this::class.java.classLoader.getResource("calendar.fxml"))
    private val parent: Parent = this.fxmlLoader.load()
    private val controller: CalendarController = this.fxmlLoader.getController()

    init {
        this.stage.title = "Calendar"
        this.stage.initModality(Modality.APPLICATION_MODAL)
        this.stage.initOwner(this.primaryStage)
        this.stage.icons.add(Image("web_hi_res_512.png"))
        this.stage.scene = Scene(parent, 200.0, 200.0)
        this.controller.setPullDownList(this.timeCalendar.jsonData.map { day -> day.date })
        this.stage.show()
        this.controller.timeCalendar = this.timeCalendar
    }
}