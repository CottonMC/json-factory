package io.github.cottonmc.jsonfactory.gui

import org.pushingpixels.substance.api.skin.*
import java.awt.Window
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.swing.JFrame
import javax.swing.JRootPane
import javax.swing.SwingUtilities
import javax.swing.UIManager

object Settings {
    private val LOCATION = Paths.get("json-factory.properties")

    var playFinishedSound: Boolean = true
        set(value) {
            field = value
            save()
        }

    var theme: Theme = Theme.DEFAULT
        set(value) {
            val old = field
            field = value
            refreshTheme(value, old)
            save()
        }

    fun init() {
        if (Files.exists(LOCATION)) {
            load()
        }
        save()
        refreshTheme(theme, null)
    }

    private fun load() {
        try {
            val props = Properties()
            props.load(Files.newInputStream(LOCATION))
            playFinishedSound = props["play-finished-sound"].toString().toBoolean()
            theme = Theme.values().find { props["theme"].toString().equals(it.name, ignoreCase = true) } ?: Theme.DEFAULT
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

    private fun refreshTheme(theme: Theme, oldTheme: Theme?) {
        val themeWindowDecorations = theme.providesWindowDecorations
        val shouldRefreshDecorations =
            oldTheme != null && themeWindowDecorations != oldTheme.providesWindowDecorations

        UIManager.setLookAndFeel(when (theme) {
            Theme.Native -> UIManager.getSystemLookAndFeelClassName()
            Theme.Cerulean -> SubstanceCeruleanLookAndFeel::class.java.name
            Theme.Creme -> SubstanceCremeLookAndFeel::class.java.name
            Theme.CremeCoffee -> SubstanceCremeCoffeeLookAndFeel::class.java.name
            Theme.Graphite -> SubstanceGraphiteLookAndFeel::class.java.name
            Theme.Mariner -> SubstanceMarinerLookAndFeel::class.java.name
            Theme.Mist -> SubstanceMistSilverLookAndFeel::class.java.name
            Theme.Nebula -> SubstanceNebulaLookAndFeel::class.java.name
        })
        JFrame.setDefaultLookAndFeelDecorated(themeWindowDecorations)

        for (window in Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window)
            if (window is JFrame && shouldRefreshDecorations) {
                SwingUtilities.invokeLater {
                    // this refreshes the window decoration type (native <=> LaF)
                    window.dispose()
                    if (!themeWindowDecorations) {
                        // prevents a crash
                        window.shape = null
                    }
                    window.isUndecorated = themeWindowDecorations
                    window.rootPane.windowDecorationStyle =
                        if (themeWindowDecorations) JRootPane.FRAME
                        else JRootPane.NONE
                    window.isVisible = true
                }
            }
        }
    }

    enum class Theme(val providesWindowDecorations: Boolean = true) {
        Native(providesWindowDecorations = false),
        Cerulean,
        Creme,
        CremeCoffee,
        Graphite,
        Mariner,
        Mist,
        Nebula;

        companion object {
            val DEFAULT = Mariner
        }
    }
}
