package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.gui.util.I18n
import io.github.cottonmc.jsonfactory.frontend.plugin.*
import picocli.CommandLine
import java.nio.file.Paths
import kotlin.reflect.KClass

@CommandLine.Command(
    name = "json-factory-gui",
    mixinStandardHelpOptions = true,
    versionProvider = VersionProvider::class
)
private object Main : Runnable {
    @CommandLine.Option(
        names = ["-p", "--plugin-classes"],
        description = ["list of plugin classes that will be loaded from the classpath"]
    )
    var pluginClasses: Array<String> = emptyArray()

    override fun run() {
        Settings.init()
        // TODO: Show a splash screen or something during plugin loading
        val plugins = loadPlugins(pluginClasses)

        val gens = sequence {
            yieldAll(Gens.ALL_GENS)

            plugins.mapNotNull(Plugin::i18n)
                .forEach(I18n::addLayer)

            yieldAll(plugins.flatMap(Plugin::generators))
        }.toList()

        Gui.createAndShow(gens, plugins.flatMap(Plugin::autoFills))
    }
}

fun main(args: Array<String>) = CommandLine.run(Main, *args)

/**
 * Loads the plugins from the classpath ([classes]) and from the file system.
 * @return a list of plugins
 */
private fun loadPlugins(classes: Array<String>): List<Plugin> = sequence {
    yieldAll(JarPluginLoader(Paths.get("plugins")).loadRecursively())

    @Suppress("UNCHECKED_CAST")
    yieldAll(
        ClasspathPluginLoader(
            classes.map {
                Class.forName(it).kotlin as KClass<out Plugin>
            }
        ).loadRecursively()
    )
}.map(PluginContainer::plugin).toList()
