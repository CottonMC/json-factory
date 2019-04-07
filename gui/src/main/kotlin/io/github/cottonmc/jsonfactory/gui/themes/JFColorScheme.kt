package io.github.cottonmc.jsonfactory.gui.themes

import org.pushingpixels.substance.api.colorscheme.BaseColorScheme
import java.awt.Color

data class JFColorScheme(
    val name: String,
    @get:JvmName("isDarkJF") val isDark: Boolean,
    val foreground: Color,
    val dark: Color,
    val mid: Color,
    val light: Color,
    val ultraDark: Color,
    val extraLight: Color,
    val ultraLight: Color
) : BaseColorScheme(name, isDark) {
    override fun getForegroundColor() = foreground
    override fun getDarkColor() = dark
    override fun getMidColor() = mid
    override fun getLightColor() = light
    override fun getUltraDarkColor() = ultraDark
    override fun getExtraLightColor() = extraLight
    override fun getUltraLightColor() = ultraLight
}
