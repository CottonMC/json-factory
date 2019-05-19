package io.github.cottonmc.jsonfactory.gui.components

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import javax.swing.JLabel

open class JFLabel(private val l10nKey: String, vararg l10nParameters: Any?, private val textDecorator: (String) -> String = { it }) :
    JLabel(textDecorator(I18n(l10nKey, *l10nParameters))) {
    init {
        I18n.addLocaleChangeListener { _, _ -> text = textDecorator(I18n(l10nKey, *l10nParameters)) }
    }

    override fun getToolTipText() = I18n.getOptional(Gui.getDescriptionKey(l10nKey))
}