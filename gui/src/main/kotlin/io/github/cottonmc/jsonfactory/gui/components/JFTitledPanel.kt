package io.github.cottonmc.jsonfactory.gui.components

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import org.jdesktop.swingx.JXTitledPanel
import java.awt.Container

internal class JFTitledPanel(private val l10nKey: String, content: Container, textDecorator: (String) -> String = { it }) :
    JXTitledPanel(textDecorator(I18n[l10nKey]), content) {
    init {
        I18n.addLocaleChangeListener { _, _ ->
            title = textDecorator(I18n[l10nKey])
            revalidate()
        }
    }

    override fun getToolTipText() = I18n.getOptional(Gui.getDescriptionKey(l10nKey))
}
