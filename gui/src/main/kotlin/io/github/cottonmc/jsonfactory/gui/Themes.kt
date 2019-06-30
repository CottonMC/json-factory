package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gui.api.theme.Theme
import io.github.cottonmc.jsonfactory.gui.api.theme.Theme.Companion.lookAndFeel
import io.github.cottonmc.jsonfactory.gui.api.theme.Theme.DefaultGroup.Dark
import io.github.cottonmc.jsonfactory.gui.api.theme.Theme.DefaultGroup.Light
import io.github.cottonmc.jsonfactory.gui.themes.SolarizedSkin
import org.pushingpixels.substance.api.skin.*
import javax.swing.UIManager

enum class Themes(
    override val lookAndFeel: Theme.LookAndFeelInitializer,
    override val group: Theme.Group = Light
) : Theme {
    Native(lookAndFeel(UIManager.getSystemLookAndFeelClassName())),
    Business(lookAndFeel { SubstanceBusinessLookAndFeel() }),
    BusinessBlackSteel(lookAndFeel { SubstanceBusinessBlackSteelLookAndFeel() }),
    BusinessBlueSteel(lookAndFeel { SubstanceBusinessBlueSteelLookAndFeel() }),
    Cerulean(lookAndFeel { SubstanceCeruleanLookAndFeel() }),
    ChallengerDeep(lookAndFeel { SubstanceChallengerDeepLookAndFeel() }, Dark),
    Creme(lookAndFeel { SubstanceCremeLookAndFeel() }),
    CremeCoffee(lookAndFeel { SubstanceCremeCoffeeLookAndFeel() }),
    Dust(lookAndFeel { SubstanceDustLookAndFeel() }),
    DustCoffee(lookAndFeel { SubstanceDustCoffeeLookAndFeel() }),
    EmeraldDusk(lookAndFeel { SubstanceEmeraldDuskLookAndFeel() }, Dark),
    Gemini(lookAndFeel { SubstanceGeminiLookAndFeel() }),
    Graphite(lookAndFeel { SubstanceGraphiteLookAndFeel() }, Dark),
    Magellan(lookAndFeel { SubstanceMagellanLookAndFeel() }, Dark),
    Mariner(lookAndFeel { SubstanceMarinerLookAndFeel() }),
    MistAqua(lookAndFeel { SubstanceMistAquaLookAndFeel() }),
    MistSilver(lookAndFeel { SubstanceMistSilverLookAndFeel() }),
    Nebula(lookAndFeel { SubstanceNebulaLookAndFeel() }),
    NebulaBrickWall(lookAndFeel { SubstanceNebulaBrickWallLookAndFeel() }),
    OfficeBlack(lookAndFeel { SubstanceOfficeBlack2007LookAndFeel() }),
    OfficeBlue(lookAndFeel { SubstanceOfficeBlue2007LookAndFeel() }),
    OfficeSilver(lookAndFeel { SubstanceOfficeSilver2007LookAndFeel() }),
    Twilight(lookAndFeel { SubstanceTwilightLookAndFeel() }, Dark),
    SolarizedLight(lookAndFeel { SolarizedSkin.LightLAF() }),
    SolarizedDark(lookAndFeel { SolarizedSkin.DarkLAF() }, Dark),
    ;

    override val id = "default.$name"

    companion object {
        /**
         * The default theme. Plugins can change this.
         */
        var default: Theme = Mariner
    }
}
