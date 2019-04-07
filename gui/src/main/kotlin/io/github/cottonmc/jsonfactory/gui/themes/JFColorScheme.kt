package io.github.cottonmc.jsonfactory.gui.themes

import org.pushingpixels.substance.api.colorscheme.BaseColorScheme
import java.awt.Color

class JFColorScheme(
    name: String, isDark: Boolean,
    private val foreground: Color,
    private val dark: Color,
    private val mid: Color,
    private val light: Color,
    private val ultraDark: Color,
    private val extraLight: Color,
    private val ultraLight: Color
) : BaseColorScheme(name, isDark) {
    override fun getForegroundColor() = foreground
    override fun getDarkColor() = dark
    override fun getMidColor() = mid
    override fun getLightColor() = light
    override fun getUltraDarkColor() = ultraDark
    override fun getExtraLightColor() = extraLight
    override fun getUltraLightColor() = ultraLight

    fun withDarkness(isDark: Boolean, name: String = displayName) =
        if (isDark == this.isDark) this
        else JFColorScheme(
            name, isDark,
            foreground, dark, mid, light, ultraDark, extraLight, ultraLight
        )
}
