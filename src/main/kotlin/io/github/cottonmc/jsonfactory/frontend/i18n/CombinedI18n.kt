package io.github.cottonmc.jsonfactory.frontend.i18n

import java.util.Locale

/**
 * An [I18n] instance that combines a [primary] `I18n` and a [secondary] `I18n`.
 * @see get
 */
class CombinedI18n(private val primary: I18n, private val secondary: I18n) : I18n {
    /**
     * The primary backing instance's [I18n.locale].
     * Setting this property sets *both* backing instances' property.
     */
    override var locale: Locale
        get() = primary.locale
        set(value) {
            primary.locale = value
            secondary.locale = value
        }

    /**
     * Returns true if either backing `I18n` instance contains the [key].
     */
    override fun containsKey(key: String) = primary.containsKey(key) || secondary.containsKey(key)

    /**
     * Adds the [listener] to both backing instances.
     */
    override fun addLocaleChangeListener(listener: LocaleChangeListener) {
        primary.addLocaleChangeListener(listener)
        secondary.addLocaleChangeListener(listener)
    }

    /**
     * Gets a localized string from a backing instance,
     * prioritizing the primary instance.
     */
    override fun get(key: String, vararg parameters: Any?) =
        if (primary.containsKey(key)) primary(key, parameters)
        else secondary(key, parameters)
}
