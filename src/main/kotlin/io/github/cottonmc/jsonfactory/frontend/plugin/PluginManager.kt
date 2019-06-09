package io.github.cottonmc.jsonfactory.frontend.plugin

/**
 * A utility object, used for loading plugins.
 */
object PluginManager {
    /**
     * Loads plugins in the [context] using the [loader].
     */
    fun loadPlugins(loader: PluginLoader, context: PluginLoadingContext): List<PluginContainer> {
        return loader.loadPlugins(context).flatMap {
            sequence {
                yield(it)

                it.plugin.loader?.let { loader ->
                    loadPlugins(loader, context)
                }
            }.asIterable()
        }
    }
}
