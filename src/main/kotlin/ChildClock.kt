
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage

class ChildClock : Application() {
    override fun start(primaryStage: Stage) {
        primaryStage.title = "Child Clock"
        primaryStage.icons.add(Image("web_hi_res_512.png"))
        primaryStage.scene = Scene(FXMLLoader.load<Parent>(this.javaClass.getResource("childclock.fxml")), 300.0, 300.0)
        primaryStage.show()
    }
}