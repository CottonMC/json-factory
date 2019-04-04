package io.github.cottonmc.jsonfactory.gui

import java.awt.Window
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.swing.SwingUtilities
import javax.swing.UIManager
import javax.swing.plaf.nimbus.NimbusLookAndFeel

object Settings {
    private val LOCATION = Paths.get("json-factory.properties")

    var playFinishedSound: Boolean = true
        set(value) {
            field = value
            save()
        }

    var theme: Theme = Theme.Native
        set(value) {
            field = value
            refreshTheme(value)
            save()
        }

    fun init() {
        if (Files.notExists(LOCATION)) {
            save()
            refreshTheme(theme)
        } else {
            load()
        }
    }

    private fun load() {
        try {
            val props = Properties()
            props.load(Files.newInputStream(LOCATION))
            playFinishedSound = props["play-finished-sound"].toString().toBoolean()
            theme = Theme.values().find { props["theme"].toString().equals(it.name, ignoreCase = true) } ?: Theme.Native
        } catch (e: IOException) {
            // TODO: Exception handling
        }
    }

    fun save() {
        val props = createProperties()

        try {
            props.store(Files.newOutputStream(LOCATION), null)
        } catch (e: IOException) {
            // TODO: Exception handling
        }
    }

    private fun createProperties(): Properties =
        Properties().apply {
            put("play-finished-sound", playFinishedSound.toString())
            put("theme", theme.name)
        }

    private fun refreshTheme(theme: Theme) {
        UIManager.setLookAndFeel(when (theme) {
            Theme.Native -> UIManager.getSystemLookAndFeelClassName()
            Theme.Nimbus -> NimbusLookAndFeel::class.java.name
            Theme.Java -> UIManager.getCrossPlatformLookAndFeelClassName()
        })

        for (window in Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window)
        }
    }

    enum class Theme {
        Native, Nimbus, Java
    }
}
