package io.github.cottonmc.jsonfactory.frontend.plugin

/**
 * Utilities for loading plugins.
 */
object PluginManager {
    /**
     * Loads plugins using the [loader].
     */
    fun loadPlugins(loader: PluginLoader): List<PluginContainer> {
        return loader.loadPlugins().flatMap {
            sequence {
                yield(it)

                it.plugin.loader?.let { loader ->
                    loadPlugins(loader)
                }
            }.asIterable()
        }
    }
}
