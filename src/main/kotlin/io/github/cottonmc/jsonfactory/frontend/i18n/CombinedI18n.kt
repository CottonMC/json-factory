package io.github.cottonmc.jsonfactory.frontend.i18n

import java.util.Locale

class CombinedI18n(private val primary: I18n, private val secondary: I18n) : I18n {
    override var locale: Locale
        get() = primary.locale
        set(value) {
            primary.locale = value
            secondary.locale = value
        }

    override fun containsKey(key: String) = primary.containsKey(key) || secondary.containsKey(key)

    override fun addLocaleChangeListener(listener: LocaleChangeListener) {
        primary.addLocaleChangeListener(listener)
        secondary.addLocaleChangeListener(listener)
    }

    override fun get(key: String, vararg parameters: Any?) =
        if (primary.containsKey(key)) primary(key, parameters)
        else secondary(key, parameters)
}
