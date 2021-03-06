package io.github.cottonmc.jsonfactory.gui.components.translatable

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import org.jdesktop.swingx.JXTitledPanel
import java.awt.Container

internal class JFTitledPanel(
    private val translationKey: String,
    content: Container,
    textDecorator: (String) -> String = { it }
) : JXTitledPanel(textDecorator(I18n[translationKey]), content) {
    init {
        I18n.addLocaleChangeListener { _, _ ->
            title = textDecorator(I18n[translationKey])
            revalidate()
        }
    }

    override fun getToolTipText() = I18n.getOptional(Gui.getDescriptionKey(translationKey))
}
