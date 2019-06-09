package io.github.cottonmc.jsonfactory.frontend.plugin

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

class ClasspathPluginLoader(private val classes: List<KClass<out Plugin>>) : PluginLoader {
    override fun loadPlugins(context: PluginLoadingContext) =
        classes.mapIndexed { index, clazz ->
            PluginContainer(clazz.objectInstance ?: clazz.createInstance(), "plugin_${hashCode()}_$index", "1")
        }
}
