package io.github.cottonmc.jsonfactory.gui

import com.google.common.flogger.FluentLogger
import io.github.cottonmc.jsonfactory.gui.api.theme.Theme
import org.jdesktop.swingx.JXErrorPane
import org.jdesktop.swingx.JXTipOfTheDay
import java.awt.Window
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.swing.SwingUtilities

internal class Settings(pluginThemes: List<Theme>) {
    private val _themes: MutableMap<String, Theme> =
        Themes.values().associateBy(Theme::id).toMutableMap()

    val themes: Map<String, Theme> get() = _themes

    var playFinishedSound: Boolean = true
        set(value) {
            field = value
            save()
        }

    var showTipsOnStartup: Boolean = true
        set(value) {
            field = value
            save()
        }

    var theme: Theme = Themes.default
        set(value) {
            field = value
            refreshTheme(value)
            save()
        }

    init {
        pluginThemes.forEach {
            val key = it.id
            if (themes.containsKey(key))
                logger.atWarning().log("Theme with $key already in map")
            else
                _themes[key] = it
        }
    }

    fun init() {
        if (Files.exists(LOCATION)) {
            load()
        }
        save()
        refreshTheme(theme)
    }

    private fun load() {
        try {
            val props = Properties()
            props.load(Files.newInputStream(LOCATION))
            playFinishedSound = props["play-finished-sound"].toString().toBoolean()
            showTipsOnStartup = props["show-tips-on-startup"].toString().toBoolean()
            theme = themes[props["key"].toString()] ?: Themes.default
        } catch (e: IOException) {
            JXErrorPane.showDialog(e)
        }
    }

    fun save() {
        val props = createProperties()

        try {
            props.store(Files.newOutputStream(LOCATION), null)
        } catch (e: IOException) {
            JXErrorPane.showDialog(e)
        }
    }

    /**
     * Used to make sure that the TotD dialog gets displayed when the user manually views it,
     * but the correct value of [showTipsOnStartup] gets displayed in it.
     *
     * @param initialValue if not `null`, the result returns it from `isShowOnStartup` on the first call
     */
    fun createTipOfTheDayChoice(initialValue: Boolean? = null): JXTipOfTheDay.ShowOnStartupChoice =
        ShowOnStartupChoiceImpl(initialValue)

    private fun createProperties(): Properties =
        Properties().apply {
            put("play-finished-sound", playFinishedSound.toString())
            put("show-tips-on-startup", showTipsOnStartup.toString())
            put("theme", theme.id)
        }

    private fun refreshTheme(theme: Theme) = SwingUtilities.invokeLater {
        theme.lookAndFeel.apply()

        for (window in Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window)
        }
    }

    private inner class ShowOnStartupChoiceImpl(val initialValue: Boolean?) : JXTipOfTheDay.ShowOnStartupChoice {
        private var called = false

        override fun isShowingOnStartup(): Boolean {
            if (!called) {
                called = true
                return initialValue ?: showTipsOnStartup
            }

            return showTipsOnStartup
        }

        override fun setShowingOnStartup(showOnStartup: Boolean) {
            showTipsOnStartup = showOnStartup
        }
    }

    companion object {
        private val LOCATION = Paths.get("json-factory.properties")
        private val logger = FluentLogger.forEnclosingClass()
    }
}
