package io.github.cottonmc.jsonfactory.frontend.i18n

import java.util.Locale

/**
 * A listener that will be called when the [locale][I18n.locale] of an [I18n] object is changed.
 */
typealias LocaleChangeListener = (old: Locale, new: Locale) -> Unit

/**
 * An interface for providing localized strings.
 * @since 0.5.0
 */
interface I18n {
    /**
     * The current locale.
     */
    // TODO: var or not?
    var locale: Locale

    /**
     * Returns true if this `I18n` can supply a localized string for the [key].
     */
    fun containsKey(key: String): Boolean

    /**
     * Adds a [listener] listening to [locale] changes.
     */
    // TODO: see todo on locale
    fun addLocaleChangeListener(listener: LocaleChangeListener)

    /**
     * Gets a localized string with the [key] and formats it with the [parameters].
     * Returns the [key] if the string is not found.
     */
    operator fun get(key: String, vararg parameters: Any?): String

    /**
     * Gets a localized string with the [key] and the [parameters].
     * Returns `null` if the string is not found.
     */
    fun getOptional(key: String, vararg parameters: Any?): String? =
        if (containsKey(key)) get(key, *parameters)
        else null
}

/**
 * Gets a localized string with the [key] and formats it with the [parameters].
 * Returns the [key] if the string is not found.
 */
// The reason for not using get here is KT-20113; the spread operator
// doesn't work when combined with the indexing operator
@JvmName("getSpread")
@Suppress("NOTHING_TO_INLINE")
inline operator fun I18n.invoke(key: String, parameters: Array<out Any?>): String = get(key, *parameters)
