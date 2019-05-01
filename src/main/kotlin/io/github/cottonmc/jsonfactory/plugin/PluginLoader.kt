package io.github.cottonmc.jsonfactory.plugin

interface PluginLoader {
    fun loadPlugins(context: PluginLoadingContext): List<PluginContainer>
}
