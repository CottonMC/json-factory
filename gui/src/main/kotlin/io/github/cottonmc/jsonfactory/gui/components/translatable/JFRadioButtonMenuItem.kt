package io.github.cottonmc.jsonfactory.gui.components.translatable

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import javax.swing.JRadioButtonMenuItem

internal class JFRadioButtonMenuItem(
    private val translationKey: String
) : JRadioButtonMenuItem(I18n[translationKey]) {
    init {
        I18n.addLocaleChangeListener { _, _ -> text = I18n[translationKey] }
    }

    override fun getToolTipText() = I18n.getOptional(Gui.getDescriptionKey(translationKey))
}
