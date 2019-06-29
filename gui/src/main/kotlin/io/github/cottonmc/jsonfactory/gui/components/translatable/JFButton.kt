package io.github.cottonmc.jsonfactory.gui.components.translatable

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import org.jdesktop.swingx.JXButton

internal open class JFButton(private val l10nKey: String, textDecorator: (String) -> String = { it }) :
    JXButton(textDecorator(I18n[l10nKey])) {
    init {
        I18n.addLocaleChangeListener { _, _ -> text = textDecorator(I18n[l10nKey]) }
    }

    override fun getToolTipText() = I18n.getOptional(Gui.getDescriptionKey(l10nKey))
}
