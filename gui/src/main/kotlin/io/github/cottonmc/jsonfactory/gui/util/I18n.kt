package io.github.cottonmc.jsonfactory.gui.util

import io.github.cottonmc.jsonfactory.frontend.I18n
import io.github.cottonmc.jsonfactory.frontend.LocaleChangeListener

object I18n {
    private val backend = I18n()
    private val guiStrings = I18n("json-factory.i18n.I18n-gui")

    operator fun invoke(key: String, vararg parameters: Any?) =
        if (guiStrings.bundle.containsKey(key)) guiStrings(key, parameters)
        else backend(key, parameters)

    fun getOptional(key: String, vararg parameters: Any?) =
        if (guiStrings.bundle.containsKey(key)) guiStrings.getOptional(key, *parameters)
        else backend.getOptional(key, *parameters)

    fun addLocaleChangeListener(listener: LocaleChangeListener) {
        backend.addLocaleChangeListener(listener)
        guiStrings.addLocaleChangeListener(listener)
    }
}