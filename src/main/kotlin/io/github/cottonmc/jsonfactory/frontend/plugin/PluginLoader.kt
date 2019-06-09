package io.github.cottonmc.jsonfactory.frontend.plugin

interface PluginLoader {
    fun loadPlugins(): List<PluginContainer>
}
