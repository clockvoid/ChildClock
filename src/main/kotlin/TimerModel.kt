
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.util.Duration

class TimerModel(private val controller: ChildClockController) {
    private var sec: Int = 0
    private var min: Int = 0
    private var hour: Int = 0
    var isMove: Boolean = true
        private set
    private val timer = Timeline(KeyFrame(Duration.millis(1000.0), EventHandler<ActionEvent> { updateTime() }))

    init {
        this.timer.cycleCount = Timeline.INDEFINITE
        this.timer.play()
    }

    private fun updateTime() {
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
        this.timer.stop()
    }

    private fun start() {
        this.isMove = true
        this.controller.setButtonText("STOP")
        this.timer.play()
    }

    fun reset() {
        this.sec = 0
        this.min = 0
        this.hour = 0
        this.isMove = true
    }
}