package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.gui.util.I18n
import io.github.cottonmc.jsonfactory.plugin.*
import picocli.CommandLine
import java.io.File
import kotlin.reflect.KClass

@CommandLine.Command(name = "json-factory-gui", mixinStandardHelpOptions = true, versionProvider = VersionProvider::class)
private object Main : Runnable {
    @CommandLine.Option(names = ["-p", "--plugin-classes"], description = ["list of plugin classes that will be loaded from the classpath"])
    var pluginClasses: Array<String> = emptyArray()

    override fun run() {
        Settings.init()
        // TODO: Show a splash screen or something during plugin loading
        Gui.createAndShow(loadPlugins(pluginClasses))
    }
}

fun main(args: Array<String>) = CommandLine.run(Main, *args)

/**
 * Loads the plugins from the classpath ([classes]) and from the file system.
 * @return a list of plugin content generators
 */
private fun loadPlugins(classes: Array<String>): List<ContentGenerator> = sequence {
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

    plugins.map(PluginContainer::plugin)
        .mapNotNull(Plugin::i18n)
        .forEach(I18n::addLayer)

    yieldAll(plugins.flatMap { it.plugin.generators.asSequence() })
}.toList()
