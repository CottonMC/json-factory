package io.github.cottonmc.jsonfactory.frontend.plugin

interface PluginLoader {
    fun loadPlugins(context: PluginLoadingContext): List<PluginContainer>
}
