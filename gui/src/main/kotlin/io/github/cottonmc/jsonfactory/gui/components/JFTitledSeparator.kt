package io.github.cottonmc.jsonfactory.gui.components

import io.github.cottonmc.jsonfactory.gui.Gui
import org.jdesktop.swingx.JXTitledSeparator

class JFTitledSeparator(private val l10nKey: String, private val textDecorator: (String) -> String = { it }) :
    JXTitledSeparator(textDecorator(Gui.I18N[l10nKey])) {
    init {
        Gui.I18N.addLocaleChangeListener { _, _ ->
            title = textDecorator(Gui.I18N[l10nKey])
            revalidate()
        }
    }

    override fun getToolTipText() = Gui.I18N.getOptional(Gui.getDescriptionKey(l10nKey))
}
