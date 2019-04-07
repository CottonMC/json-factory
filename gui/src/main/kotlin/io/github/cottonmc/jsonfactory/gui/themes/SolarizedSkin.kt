package io.github.cottonmc.jsonfactory.gui.themes

import org.pushingpixels.substance.api.*
import org.pushingpixels.substance.api.painter.border.FlatBorderPainter
import org.pushingpixels.substance.api.painter.decoration.FlatDecorationPainter
import org.pushingpixels.substance.api.painter.fill.MatteFillPainter
import org.pushingpixels.substance.api.painter.highlight.GlassHighlightPainter
import org.pushingpixels.substance.api.shaper.ClassicButtonShaper
import java.awt.Color

class SolarizedSkin(private val name: String, isDark: Boolean) : SubstanceSkin() {
    init {
        val colorScheme = if (isDark) DARK_SCHEME else LIGHT_SCHEME

        registerDecorationAreaSchemeBundle(
            SubstanceColorSchemeBundle(colorScheme, colorScheme, colorScheme),
            DecorationAreaType.NONE
        )

        buttonShaper = ClassicButtonShaper()
        fillPainter = MatteFillPainter()
        borderPainter = FlatBorderPainter()
        decorationPainter = FlatDecorationPainter()
        highlightPainter = GlassHighlightPainter()
    }

    override fun getDisplayName() = name

    companion object {
        val BASE03 = Color(0x002b36)
        val BASE02 = Color(0x073642)
        val BASE01 = Color(0x586e75)
        val BASE00 = Color(0x657b83)
        val BASE0 = Color(0x839496)
        val BASE1 = Color(0x93a1a1)
        val BASE2 = Color(0xeee8d5)
        val BASE3 = Color(0xfdf6e3)

        val LIGHT_SCHEME = JFColorScheme(
            "Solarized Light", false,
            foreground = BASE00,
            dark = BASE2,
            mid = BASE2,
            light = BASE3,
            ultraDark = BASE1,
            extraLight = BASE3,
            ultraLight = BASE2
        )

        val DARK_SCHEME = JFColorScheme(
            "Solarized Dark", /* a bit hacky */ false,
            foreground = BASE0,
            dark = BASE02,
            mid = BASE02,
            light = BASE03,
            ultraDark = BASE01,
            extraLight = BASE03,
            ultraLight = BASE02
        )

          // Reference color scheme
//        val COLOR_SCHEME = JFColorScheme(
//            "Test", false,
//            Color.BLACK,
//            Color.YELLOW,
//            Color.GREEN,
//            Color.CYAN,
//            Color.BLUE,
//            Color.MAGENTA,
//            Color.PINK
//        )
    }

    class LightLAF : SubstanceLookAndFeel(SolarizedSkin("Solarized Light", false))
    class DarkLAF : SubstanceLookAndFeel(SolarizedSkin("Solarized Dark", true))
}
