package io.github.cottonmc.jsonfactory.gui.components

import io.github.cottonmc.jsonfactory.gui.Gui
import javax.swing.JCheckBox

class JFCheckBox(
    private val l10nKey: String,
    selected: Boolean = false,
    private val textDecorator: (String) -> String = { it }
) : JCheckBox(textDecorator(Gui.I18N[l10nKey]), selected) {
    init {
        Gui.I18N.addLocaleChangeListener { _, _ -> text = textDecorator(Gui.I18N[l10nKey]) }
    }

    override fun getToolTipText() = Gui.I18N.getOptional(Gui.getDescriptionKey(l10nKey))
}
