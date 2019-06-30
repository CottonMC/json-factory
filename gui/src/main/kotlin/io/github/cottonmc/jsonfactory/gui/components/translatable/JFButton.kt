package io.github.cottonmc.jsonfactory.gui.components.translatable

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import org.jdesktop.swingx.JXButton

internal open class JFButton(private val translationKey: String, textDecorator: (String) -> String = { it }) :
    JXButton(textDecorator(I18n[translationKey])) {
    init {
        I18n.addLocaleChangeListener { _, _ -> text = textDecorator(I18n[translationKey]) }
    }

    override fun getToolTipText() = I18n.getOptional(Gui.getDescriptionKey(translationKey))
}
