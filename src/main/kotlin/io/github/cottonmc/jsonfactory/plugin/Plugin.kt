package io.github.cottonmc.jsonfactory.plugin

import io.github.cottonmc.jsonfactory.frontend.i18n.I18n
import io.github.cottonmc.jsonfactory.gens.ContentGenerator

/**
 * A plugin for a json-factory frontend.
 *
 * A plugin can provide its own [loader] that can load more plugins.
 * It also has a list of [generators].
 */
interface Plugin {
    /**
     * An optional [PluginLoader].
     */
    val loader: PluginLoader? get() = null

    /**
     * A list of [ContentGenerator]s.
     */
    val generators: List<ContentGenerator> get() = emptyList()

    /**
     * An [I18n] for this plugin's strings.
     */
    val i18n: I18n? get() = null
}
