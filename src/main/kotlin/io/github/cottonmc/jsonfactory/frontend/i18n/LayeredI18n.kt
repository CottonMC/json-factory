package io.github.cottonmc.jsonfactory.frontend.i18n

import java.util.Locale

/* TODO: Cleanup
 *   Most of the openness here is for gui.util.I18n;
 *   this class should be made final again and the properties should be private.
 */

/**
 * An [I18n] instance that combines a [primary] `I18n` and a secondary `I18n` [layers].
 * @see get
 */
open class LayeredI18n(protected val primary: I18n, protected val layers: Iterable<I18n>) : I18n {
    /**
     * The primary backing instance's [I18n.locale].
     * Setting this property sets *both* backing instances' property.
     */
    override var locale: Locale
        get() = primary.locale
        set(value) {
            primary.locale = value
            layers.forEach { it.locale = value }
        }

    /**
     * Returns true if either the primary `I18n` instance or any of the layers contains the [key].
     */
    override fun containsKey(key: String) = primary.containsKey(key) || layers.any { it.containsKey(key) }

    /**
     * Adds the [listener] to both backing instances.
     */
    override fun addLocaleChangeListener(listener: LocaleChangeListener) {
        primary.addLocaleChangeListener(listener)
        layers.forEach { it.addLocaleChangeListener(listener) }
    }

    /**
     * Gets a localized string from a backing instance,
     * prioritizing the primary instance.
     */
    override fun get(key: String, vararg parameters: Any?): String {
        if (primary.containsKey(key))
            return primary(key, parameters)
        else {
            val iterator = layers.iterator()
            while (iterator.hasNext()) {
                val layer = iterator.next()
                if (layer.containsKey(key) || !iterator.hasNext())
                    return layer(key, parameters)
            }
        }

        throw IllegalStateException("No layers contained the key and iterator has remaining elements")
    }
}
