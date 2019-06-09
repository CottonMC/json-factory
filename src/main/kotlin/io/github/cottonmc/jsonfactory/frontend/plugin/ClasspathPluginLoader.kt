package io.github.cottonmc.jsonfactory.frontend.plugin

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

/**
 * Loads plugins from a list of plugin [classes] that are converted to instances.
 * A plugin class should be either a Kotlin `object` or have an empty constructor.
 */
class ClasspathPluginLoader(private val classes: List<KClass<out Plugin>>) : PluginLoader {
    override fun loadPlugins() =
        classes.mapIndexed { index, clazz ->
            PluginContainer(clazz.objectInstance ?: clazz.createInstance(), "plugin_${hashCode()}_$index", "1")
        }
}
