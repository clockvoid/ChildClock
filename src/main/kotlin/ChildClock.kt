
import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import java.awt.MenuItem
import java.awt.PopupMenu
import java.awt.SystemTray
import java.awt.TrayIcon
import javax.imageio.ImageIO

class ChildClock : Application() {
    private lateinit var tray: SystemTray
    private lateinit var icon: TrayIcon
    private lateinit var popupMenu: PopupMenu

    override fun init() {
        // Task Tray Icon Initialisation
        this.tray = SystemTray.getSystemTray()
        this.icon = TrayIcon(ImageIO.read(this.javaClass.getResource("web_hi_res_512.png")))
        // change icon resizeable
        this.icon.isImageAutoSize = true
        // init popup menu
        this.popupMenu = PopupMenu()
        // init exit menu exit to push
        val exitMenu  = MenuItem("Exit")
        exitMenu.addActionListener {
            Platform.exit() // In JavaFX, we have to use Platform.exit() because of Thread problems
        }
        this.popupMenu.add(exitMenu)
        this.icon.popupMenu = this.popupMenu
    }

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Child Clock"
        primaryStage.icons.add(Image("web_hi_res_512.png"))
        primaryStage.scene = Scene(FXMLLoader.load<Parent>(this.javaClass.getResource("childclock.fxml")), 300.0, 300.0)
        primaryStage.show()
        this.icon.addActionListener { Platform.runLater({ primaryStage.show() }) }
        this.tray.add(this.icon)
        Platform.setImplicitExit(false)
    }

    override fun stop() {
        SystemTray.getSystemTray().remove(icon)
        super.stop()
    }
}