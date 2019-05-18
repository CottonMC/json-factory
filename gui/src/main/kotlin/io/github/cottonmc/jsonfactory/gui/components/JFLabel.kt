package io.github.cottonmc.jsonfactory.gui.components

import io.github.cottonmc.jsonfactory.gui.Gui
import io.github.cottonmc.jsonfactory.gui.util.maybeInvoke
import org.jdesktop.swingx.JXLabel
import javax.swing.JLabel

open class JFLabel(private val l10nKey: String, private val textDecorator: (String) -> String = { it }) : JLabel(textDecorator(Gui.I18N[l10nKey])) {
    init {
        Gui.I18N.addLocaleChangeListener { _, _ -> text = textDecorator(Gui.I18N[l10nKey]) }
    }

    override fun getToolTipText() = Gui.I18N.getOptional(Gui.getDescriptionKey(l10nKey))
}
