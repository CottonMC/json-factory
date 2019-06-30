package io.github.cottonmc.jsonfactory.gui.components.translatable

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import javax.swing.JCheckBox

internal class JFCheckBox(
    private val translationKey: String,
    selected: Boolean = false,
    textDecorator: (String) -> String = { it }
) : JCheckBox(textDecorator(I18n[translationKey]), selected) {
    init {
        I18n.addLocaleChangeListener { _, _ -> text = textDecorator(I18n[translationKey]) }
    }

    override fun getToolTipText() = I18n.getOptional(Gui.getDescriptionKey(translationKey))
}
