package io.github.cottonmc.jsonfactory.frontend

import java.text.MessageFormat
import java.util.Locale
import java.util.ResourceBundle

/**
 * A listener that will be called when the [locale][I18n.locale] of an [I18n] object is changed.
 */
typealias LocaleChangeListener = (old: Locale, new: Locale) -> Unit

/**
 * @since 0.5.0
 */
class I18n(private val bundleName: String = DEFAULT_BUNDLE, locale: Locale = Locale.getDefault()) {
    private val localeChangeListeners: MutableList<LocaleChangeListener> = ArrayList()

    var locale: Locale = locale
        set(value) {
            localeChangeListeners.forEach { it(field, value) }
            bundle = ResourceBundle.getBundle(bundleName, value)
            field = value
        }

    var bundle: ResourceBundle = ResourceBundle.getBundle(bundleName, locale)
        private set

    fun addLocaleChangeListener(listener: LocaleChangeListener) {
        localeChangeListeners += listener
    }

    /**
     * Gets a localized string with the [key] and formats it with the [parameters].
     * Returns the [key] if the string is not found.
     */
    operator fun get(key: String, vararg parameters: Any?): String =
        if (bundle.containsKey(key)) MessageFormat(bundle.getString(key), locale).format(parameters)
        else key

    /**
     * Gets a localized string with the [key] and formats it with the [parameters].
     * Returns the [key] if the string is not found.
     */
    // The reason for not using get here is KT-20113; the spread operator
    // doesn't work when combined with the indexing operator
    @JvmName("getSpread")
    @Suppress("NOTHING_TO_INLINE")
    inline operator fun invoke(key: String, parameters: Array<out Any?>): String = get(key, *parameters)

    /**
     * Gets a localized string with the [key] and the [parameters].
     * Returns `null` if the string is not found.
     */
    fun getOptional(key: String, vararg parameters: Any?): String? =
        if (bundle.containsKey(key)) get(key, *parameters)
        else null

    companion object {
        private const val DEFAULT_BUNDLE = "json-factory.i18n.I18n"
    }
}
