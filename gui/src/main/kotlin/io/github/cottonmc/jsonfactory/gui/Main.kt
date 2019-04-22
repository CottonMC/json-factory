package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.plugin.JarPluginLoader
import io.github.cottonmc.jsonfactory.plugin.PluginLoadingContext
import io.github.cottonmc.jsonfactory.plugin.PluginManager
import java.io.File

fun main() {
    Settings.init()
    // TODO: Show a splash screen or something during plugin loading
    Gui.createAndShow(loadGens())
}

private fun loadGens(): List<ContentGenerator> = sequence {
    yieldAll(Gens.allGens)
    yieldAll(PluginManager.loadPlugins(
        JarPluginLoader(), PluginLoadingContext(File("."))
    ).flatMap {
        it.plugin.generators
    })
}.toList()
