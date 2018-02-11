package com.lucciola.childclock

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

class ChildClockView {
    // Task Tray Icon Initialisation
    private var tray: SystemTray = SystemTray.getSystemTray()
    private var icon: TrayIcon = TrayIcon(ImageIO.read(this.javaClass.classLoader.getResource("web_hi_res_512.png")))
    // init popup menu
    private var popupMenu = PopupMenu()

    init {
        // change icon resizeable
        this.icon.isImageAutoSize = true
        // init exit menu exit to push
        val exitMenu  = MenuItem("Exit")
        exitMenu.addActionListener {
            Platform.exit() // In JavaFX, we have to use Platform.exit() because of Thread problems
        }
        this.popupMenu.add(exitMenu)
        this.icon.popupMenu = this.popupMenu
    }

    fun start(primaryStage: Stage) {
        primaryStage.title = "Child Clock"
        primaryStage.icons.add(Image("web_hi_res_512.png"))
        primaryStage.scene = Scene(FXMLLoader.load<Parent>(this.javaClass.classLoader.getResource("childclock.fxml")), 300.0, 300.0)
        primaryStage.show()
        this.icon.addActionListener { Platform.runLater({ primaryStage.show() }) }
        this.tray.add(this.icon)
        Platform.setImplicitExit(false)
    }

    fun stop() {
        SystemTray.getSystemTray().remove(icon)
    }
}