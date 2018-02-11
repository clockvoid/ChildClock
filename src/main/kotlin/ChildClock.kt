
import com.lucciola.childclock.ChildClockView
import javafx.application.Application
import javafx.stage.Stage

class ChildClock : Application() {
    private lateinit var view: ChildClockView

    override fun init() {
        this.view = ChildClockView()
    }

    override fun start(primaryStage: Stage) {
        this.view.start(primaryStage)
    }

    override fun stop() {
        this.view.stop()
        super.stop()
    }
}