package io.github.cottonmc.jsonfactory.gui.components.translatable

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.I18n
import org.jdesktop.swingx.JXTextField

internal class JFTextField(private val translationKey: String, private val textDecorator: (String) -> String = { it }) :
    JXTextField(textDecorator(I18n[translationKey])) {
    init {
        I18n.addLocaleChangeListener { _, _ ->
            prompt = textDecorator(I18n[translationKey])
            revalidate()
        }
    }

    override fun getToolTipText() = runCatching { I18n.getOptional(Gui.getDescriptionKey(translationKey)) }.getOrNull()
}
