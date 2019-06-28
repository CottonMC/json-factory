package io.github.cottonmc.jsonfactory.gui.components

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import javax.swing.JCheckBox

internal class JFCheckBox(
    private val l10nKey: String,
    selected: Boolean = false,
    textDecorator: (String) -> String = { it }
) : JCheckBox(textDecorator(I18n[l10nKey]), selected) {
    init {
        I18n.addLocaleChangeListener { _, _ -> text = textDecorator(I18n[l10nKey]) }
    }

    override fun getToolTipText() = I18n.getOptional(Gui.getDescriptionKey(l10nKey))
}
