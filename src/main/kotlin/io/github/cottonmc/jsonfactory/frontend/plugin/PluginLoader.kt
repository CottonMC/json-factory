@file:JvmName("PluginLoaders")
package io.github.cottonmc.jsonfactory.frontend.plugin

/**
 * An interface for loading plugins.
 */
interface PluginLoader {
    /**
     * Loads a collection of plugins.
     */
    fun loadPlugins(): Collection<PluginContainer>
}

/**
 * Loads the plugins of `this` [PluginLoader] recursively,
 * loading plugins with the plugins' [loaders][Plugin.loader] as well.
 */
fun PluginLoader.loadRecursively(): List<PluginContainer> = loadPlugins().flatMap {
    it.plugin.loader?.loadRecursively().orEmpty() + it
}
