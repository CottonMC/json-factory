package io.github.cottonmc.jsonfactory.plugin

object PluginManager {
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
