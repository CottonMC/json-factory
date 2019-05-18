package io.github.cottonmc.jsonfactory.frontend

import java.util.Locale
import java.util.ResourceBundle
import com.strategicgains.crossroads.I18n as CrossRoads

/**
 * A listener that will be called when the [locale][I18n.locale] of an [I18n] object is changed.
 */
typealias LocaleChangeListener = (old: Locale, new: Locale) -> Unit

/**
 * @since 0.5.0
 */
class I18n(locale: Locale = Locale.getDefault()) {
    private val localeChangeListeners: MutableList<LocaleChangeListener> = ArrayList()

    var locale: Locale = locale
        set(value) {
            localeChangeListeners.forEach { it(field, value) }
            field = value
        }

    val bundle: ResourceBundle by lazy { ResourceBundle.getBundle(BUNDLE_BASE_NAME) }

    fun addLocaleChangeListener(listener: LocaleChangeListener) {
        localeChangeListeners += listener
    }

    /**
     * Gets a localized string with the [key] and the [parameters].
     * Returns the [key] if the string is not found.
     */
    operator fun get(key: String, vararg parameters: Any?): String = CrossRoads.localize(key, locale, parameters)

    /**
     * Gets a localized string with the [key] and the [parameters].
     * Returns `null` if the string is not found.
     */
    fun getOptional(key: String, vararg parameters: Any?): String? =
        if (bundle.containsKey(key)) this[key, parameters]
        else null

    companion object {
        private const val BUNDLE_BASE_NAME = "json-factory.i18n.I18n"

        init {
            CrossRoads.setBaseName(BUNDLE_BASE_NAME)
        }
    }
}
