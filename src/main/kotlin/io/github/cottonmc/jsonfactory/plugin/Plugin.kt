package io.github.cottonmc.jsonfactory.plugin

import io.github.cottonmc.jsonfactory.gens.ContentGenerator

// TODO: Should this be here or in frontend?
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
}
