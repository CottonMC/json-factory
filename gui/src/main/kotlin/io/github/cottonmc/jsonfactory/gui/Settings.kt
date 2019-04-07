package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gui.themes.SolarizedSkin
import org.jdesktop.swingx.JXErrorPane
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

    var showTipsOnStartup: Boolean = true
        set(value) {
            field = value
            save()
        }

    var forceSystemWindowDecorations: Boolean = false
        set(value) {
            field = value
            if (theme.providesWindowDecorations) {
                refreshTheme(theme, null, forceRefreshDecorations = true)
            }
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
            forceSystemWindowDecorations = props["force-system-window-decorations"].toString().toBoolean()
            playFinishedSound = props["play-finished-sound"].toString().toBoolean()
            showTipsOnStartup = props["show-tips-on-startup"].toString().toBoolean()
            theme = Theme.values().find { props["theme"].toString().equals(it.name, ignoreCase = true) } ?: Theme.DEFAULT
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

    private fun createProperties(): Properties =
        Properties().apply {
            put("force-system-window-decorations", forceSystemWindowDecorations.toString())
            put("play-finished-sound", playFinishedSound.toString())
            put("show-tips-on-startup", showTipsOnStartup.toString())
            put("theme", theme.name)
        }

    private fun refreshTheme(theme: Theme, oldTheme: Theme?, forceRefreshDecorations: Boolean = false) {
        fun providesWindowDecorations(theme: Theme) =
            !forceSystemWindowDecorations && theme.providesWindowDecorations
        val themeWindowDecorations = providesWindowDecorations(theme)
        val shouldRefreshDecorations = forceRefreshDecorations ||
            (oldTheme != null && providesWindowDecorations(theme) != providesWindowDecorations(oldTheme))

        UIManager.setLookAndFeel(when (theme) {
            Theme.Native -> UIManager.getSystemLookAndFeelClassName()
            Theme.Business -> SubstanceBusinessLookAndFeel::class.java.name
            Theme.BusinessBlackSteel -> SubstanceBusinessBlackSteelLookAndFeel::class.java.name
            Theme.BusinessBlueSteel -> SubstanceBusinessBlueSteelLookAndFeel::class.java.name
            Theme.Cerulean -> SubstanceCeruleanLookAndFeel::class.java.name
            Theme.ChallengerDeep -> SubstanceChallengerDeepLookAndFeel::class.java.name
            Theme.Creme -> SubstanceCremeLookAndFeel::class.java.name
            Theme.CremeCoffee -> SubstanceCremeCoffeeLookAndFeel::class.java.name
            Theme.Dust -> SubstanceDustLookAndFeel::class.java.name
            Theme.DustCoffee -> SubstanceDustCoffeeLookAndFeel::class.java.name
            Theme.EmeraldDusk -> SubstanceEmeraldDuskLookAndFeel::class.java.name
            Theme.Gemini -> SubstanceGeminiLookAndFeel::class.java.name
            Theme.Graphite -> SubstanceGraphiteLookAndFeel::class.java.name
            Theme.Magellan -> SubstanceMagellanLookAndFeel::class.java.name
            Theme.Mariner -> SubstanceMarinerLookAndFeel::class.java.name
            Theme.MistAqua -> SubstanceMistAquaLookAndFeel::class.java.name
            Theme.MistSilver -> SubstanceMistSilverLookAndFeel::class.java.name
            Theme.Nebula -> SubstanceNebulaLookAndFeel::class.java.name
            Theme.NebulaBrickWall -> SubstanceNebulaBrickWallLookAndFeel::class.java.name
            Theme.OfficeBlack -> SubstanceOfficeBlack2007LookAndFeel::class.java.name
            Theme.OfficeBlue -> SubstanceOfficeBlue2007LookAndFeel::class.java.name
            Theme.OfficeSilver -> SubstanceOfficeSilver2007LookAndFeel::class.java.name
            Theme.Twilight -> SubstanceTwilightLookAndFeel::class.java.name
            Theme.SolarizedLight -> SolarizedSkin.LightLAF::class.java.name
            Theme.SolarizedDark -> SolarizedSkin.DarkLAF::class.java.name
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

    enum class ThemeGroup {
        Light, Dark
    }

    enum class Theme(val group: ThemeGroup = ThemeGroup.Light, val providesWindowDecorations: Boolean = true) {
        Native(providesWindowDecorations = false),
        Business,
        BusinessBlackSteel,
        BusinessBlueSteel,
        Cerulean,
        ChallengerDeep(ThemeGroup.Dark),
        Creme,
        CremeCoffee,
        Dust,
        DustCoffee,
        EmeraldDusk(ThemeGroup.Dark),
        Gemini,
        Graphite(ThemeGroup.Dark),
        Magellan(ThemeGroup.Dark),
        Mariner,
        MistAqua,
        MistSilver,
        Nebula,
        NebulaBrickWall,
        OfficeBlack,
        OfficeBlue,
        OfficeSilver,
        Twilight(ThemeGroup.Dark),
        SolarizedLight,
        SolarizedDark(Settings.ThemeGroup.Dark),
        ;

        companion object {
            val DEFAULT = Mariner
        }
    }
}
