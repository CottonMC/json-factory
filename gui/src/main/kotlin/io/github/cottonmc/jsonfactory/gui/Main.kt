package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.plugin.*
import java.io.File
import kotlin.reflect.KClass

fun main(args: Array<String>) {
    Settings.init()
    // TODO: Show a splash screen or something during plugin loading
    // TODO: Document CLI args
    Gui.createAndShow(loadGens(args))
}

private fun loadGens(classes: Array<String>): List<ContentGenerator> = sequence {
    yieldAll(Gens.ALL_GENS)

    val plugins = sequence {
        yieldAll(
            PluginManager.loadPlugins(
                JarPluginLoader(), PluginLoadingContext(File("."))
            )
        )

        @Suppress("UNCHECKED_CAST")
        yieldAll(
            PluginManager.loadPlugins(
                ClasspathPluginLoader(
                    classes.map {
                        Class.forName(it).kotlin as KClass<out Plugin>
                    }
                ),
                PluginLoadingContext(File("."))
            )
        )
    }

    yieldAll(plugins.flatMap { it.plugin.generators.asSequence() })
}.toList()
