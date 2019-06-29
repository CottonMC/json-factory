package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gui.api.theme.Theme
import io.github.cottonmc.jsonfactory.gui.themes.SolarizedSkin
import org.jdesktop.swingx.JXErrorPane
import org.jdesktop.swingx.JXTipOfTheDay
import org.pushingpixels.substance.api.skin.*
import java.awt.Window
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.swing.SwingUtilities
import javax.swing.UIManager

internal object Settings {
    private val LOCATION = Paths.get("json-factory.properties")
    private val _themes: MutableMap<String, Theme> =
        Themes.values().asSequence()
            .map { it.name.toLowerCase(Locale.ROOT) to it }
            .toMap()
            .toMutableMap()

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

    var theme: Theme = Themes.DEFAULT
        set(value) {
            field = value
            refreshTheme(value)
            save()
        }

    // TODO: Move the theme stuff to the constructor
    fun init(pluginThemes: List<Theme>) {
        pluginThemes.forEach {
            // TODO: Proper logging
            val key = it.name.toLowerCase(Locale.ROOT)
            if (themes.containsKey(key))
                println("Warn: theme with $key already in map")
            else
                _themes[key] = it
        }

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
            theme = themes[props["key"].toString().toLowerCase(Locale.ROOT)] ?: Themes.DEFAULT
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
            put("theme", theme.name)
        }

    private fun refreshTheme(theme: Theme) = SwingUtilities.invokeLater {
        theme.lookAndFeel.apply()

        for (window in Window.getWindows()) {
            SwingUtilities.updateComponentTreeUI(window)
        }
    }

    private enum class Themes(override val lookAndFeel: Theme.LookAndFeelInitializer, override val group: Theme.Group = Theme.DefaultGroup.Light) : Theme {
        Native(Theme.laf(UIManager.getSystemLookAndFeelClassName())),
        Business(Theme.laf { SubstanceBusinessLookAndFeel() }),
        BusinessBlackSteel(Theme.laf { SubstanceBusinessBlackSteelLookAndFeel() }),
        BusinessBlueSteel(Theme.laf { SubstanceBusinessBlueSteelLookAndFeel() }),
        Cerulean(Theme.laf { SubstanceCeruleanLookAndFeel() }),
        ChallengerDeep(Theme.laf { SubstanceChallengerDeepLookAndFeel() }, Theme.DefaultGroup.Dark),
        Creme(Theme.laf { SubstanceCremeLookAndFeel() }),
        CremeCoffee(Theme.laf { SubstanceCremeCoffeeLookAndFeel() }),
        Dust(Theme.laf { SubstanceDustLookAndFeel() }),
        DustCoffee(Theme.laf { SubstanceDustCoffeeLookAndFeel() }),
        EmeraldDusk(Theme.laf { SubstanceEmeraldDuskLookAndFeel() }, Theme.DefaultGroup.Dark),
        Gemini(Theme.laf { SubstanceGeminiLookAndFeel() }),
        Graphite(Theme.laf { SubstanceGraphiteLookAndFeel() }, Theme.DefaultGroup.Dark),
        Magellan(Theme.laf { SubstanceMagellanLookAndFeel() }, Theme.DefaultGroup.Dark),
        Mariner(Theme.laf { SubstanceMarinerLookAndFeel() }),
        MistAqua(Theme.laf { SubstanceMistAquaLookAndFeel() }),
        MistSilver(Theme.laf { SubstanceMistSilverLookAndFeel() }),
        Nebula(Theme.laf { SubstanceNebulaLookAndFeel() }),
        NebulaBrickWall(Theme.laf { SubstanceNebulaBrickWallLookAndFeel() }),
        OfficeBlack(Theme.laf { SubstanceOfficeBlack2007LookAndFeel() }),
        OfficeBlue(Theme.laf { SubstanceOfficeBlue2007LookAndFeel() }),
        OfficeSilver(Theme.laf { SubstanceOfficeSilver2007LookAndFeel() }),
        Twilight(Theme.laf { SubstanceTwilightLookAndFeel() }, Theme.DefaultGroup.Dark),
        SolarizedLight(Theme.laf { SolarizedSkin.LightLAF() }),
        SolarizedDark(Theme.laf { SolarizedSkin.DarkLAF() }, Theme.DefaultGroup.Dark),
        ;

        companion object {
            val DEFAULT = Mariner
        }
    }

    private class ShowOnStartupChoiceImpl(val initialValue: Boolean?) : JXTipOfTheDay.ShowOnStartupChoice {
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
}
