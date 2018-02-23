package com.lucciola.childclock

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

class TimerModel(private val controller: ChildClockController) {
    private var sec: Int = 0
    private var min: Int = 0
    private var hour: Int = 0
    var isMove: Boolean = true
        private set
    private val timer = Timeline(KeyFrame(Duration.millis(1000.0), EventHandler<ActionEvent> { updateTime() }))
    private val calendar = TimeCalendar(File("calendar.json"))
    private var date: Date = DateUtils.truncate(Date(), Calendar.DAY_OF_MONTH)

    init {
        this.timer.cycleCount = Timeline.INDEFINITE
        this.timer.play()
    }

    fun isNextDay(now: Date): Boolean {
        return this.date.after(now)
    }

    private fun updateTime() {
        val now: Date = DateUtils.truncate(Date(), Calendar.DAY_OF_MONTH)
        if (isNextDay(now)) {
            calendar.addDay(this.date, this.hour, this.min, this.sec)
            calendar.makeJson()
            calendar.writeFile()
            this.date = now
        }

        // if timer is stop
        if (!this.isMove) {
            return
        }
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
        this.controller.setTimeText(this.sec, this.min, this.hour)
    }

    fun onStartOrStop() {
        when (this.isMove) {
            true -> this.stop()
            false -> this.start()
        }
    }

    private fun stop() {
        this.isMove = false
        this.controller.setButtonText("START")
    }

    private fun start() {
        this.isMove = true
        this.controller.setButtonText("STOP")
    }

    fun reset() {
        this.sec = 0
        this.min = 0
        this.hour = 0
        this.controller.setTimeText(0, 0, 0)
        this.start()
    }

    fun createCalendar(primaryStage: Stage) {
        val calendar = CalendarView(primaryStage, this.calendar)
    }
}