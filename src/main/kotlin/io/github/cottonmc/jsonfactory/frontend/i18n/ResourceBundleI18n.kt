package io.github.cottonmc.jsonfactory.frontend.i18n

import java.text.MessageFormat
import java.util.Locale
import java.util.ResourceBundle
import kotlin.properties.Delegates

/**
 * An [I18n] instance using resource bundles at the backend.
 *
 * @param bundleName the resource bundle base name
 * @param locale the current locale
 * @since 0.5.0
 */
class ResourceBundleI18n(private val bundleName: String, locale: Locale = Locale.getDefault()) : I18n {
    private val localeChangeListeners: MutableList<LocaleChangeListener> = ArrayList()

    override var locale: Locale by Delegates.observable(locale) { _, old, new ->
        localeChangeListeners.forEach { it(old, new) }
        bundle = ResourceBundle.getBundle(bundleName, new)
    }

    private var bundle: ResourceBundle = ResourceBundle.getBundle(bundleName, locale)

    override fun containsKey(key: String) = bundle.containsKey(key)

    override fun addLocaleChangeListener(listener: LocaleChangeListener) {
        localeChangeListeners += listener
    }

    /**
     * Gets a localized string with the [key] and formats it with the [parameters].
     * Returns the [key] if the string is not found.
     */
    override operator fun get(key: String, vararg parameters: Any?): String =
        if (bundle.containsKey(key)) MessageFormat(bundle.getString(key), locale).format(parameters)
        else key

    companion object {
        private const val DEFAULT_BUNDLE = "json-factory.i18n.I18n"

        /**
         * Creates a [ResourceBundleI18n] instance that provides translated JSON Factory backend strings,
         * such as generator names.
         */
        fun createBackendI18n(): ResourceBundleI18n = ResourceBundleI18n(DEFAULT_BUNDLE)
    }
}
