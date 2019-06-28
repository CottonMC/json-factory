package io.github.cottonmc.jsonfactory.gui.components

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import org.jdesktop.swingx.JXTextField

internal class JFTextField(private val l10nKey: String, private val textDecorator: (String) -> String = { it }) :
    JXTextField(textDecorator(I18n[l10nKey])) {
    init {
        I18n.addLocaleChangeListener { _, _ ->
            prompt = textDecorator(I18n[l10nKey])
            revalidate()
        }
    }

    override fun getToolTipText() = runCatching { I18n.getOptional(Gui.getDescriptionKey(l10nKey)) }.getOrNull()
}
