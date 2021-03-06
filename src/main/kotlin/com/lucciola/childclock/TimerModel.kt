package com.lucciola.childclock

import com.lucciola.Settings
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.Stage
import javafx.util.Duration
import java.util.*
import org.apache.commons.lang3.time.DateUtils
import java.io.File
import com.lucciola.calendar.TimeCalendar
import com.lucciola.calendar.CalendarView

class TimerModel(private val controller: ChildClockController, calendarFileName: String) {
    var sec: Int = 0
    var min: Int = 0
    var hour: Int = 0
    var isMove: Boolean = true
        private set
    private val timer = Timeline(KeyFrame(Duration.millis(1000.0), EventHandler<ActionEvent> { updateTime() }))
    private val calendar = TimeCalendar(File(calendarFileName))
    private var date: Date = DateUtils.truncate(Date(), Calendar.DAY_OF_MONTH)
    private val settings = Settings(File("settings.json"))

    init {
        this.timer.cycleCount = Timeline.INDEFINITE
        this.timer.play()
    }

    fun recordDayData(now: Date) {
        if (now.after(this.date)) {
            this.calendar.addDay(this.date, this.hour, this.min, this.sec)
            this.calendar.makeJson()
            this.calendar.writeFile()
            this.date = now
            this.reset()
            this.stop()
        }
    }

    fun nextTime() {
        when {
            this.sec == 50 && this.min == 50 -> {
                this.hour++
                this.sec = 0
                this.min = 0
            }
            this.sec == 50 -> {
                this.min++
                this.sec = 0
            }
            else -> {
                this.sec++
            }
        }
        if (this.settings.setting.alert
                && this.hour == this.settings.setting.alert_time[0]
                && this.min == this.settings.setting.alert_time[1]
                && this.sec == this.settings.setting.alert_time[2]) {
            this.controller.makeDialog(this.settings.setting.alert_title
                    ,this.settings.setting.alert_header
                    , this.settings.setting.alert_content)
        }
        this.controller.setTimeText(this.sec, this.min, this.hour)
    }

    private fun updateTime() {
        val now: Date = DateUtils.truncate(Date(), Calendar.DAY_OF_MONTH)
        this.recordDayData(now)
        // if timer is stopped
        when (this.isMove) {
            true -> this.nextTime()
        }
    }

    fun onStartOrStop() {
        when (this.isMove) {
            true -> this.stop()
            false -> this.start()
        }
    }

    fun stop() {
        this.isMove = false
        this.controller.setButtonText("START")
    }

    fun start() {
        this.isMove = true
        this.controller.setButtonText("STOP")
    }

    fun reset() {
        this.sec = 0
        this.min = 0
        this.hour = 0
        this.controller.setTimeText(0, 0, 0)
    }

    fun createCalendar(primaryStage: Stage) {
        val calendar = CalendarView(primaryStage, this.calendar)
    }
}