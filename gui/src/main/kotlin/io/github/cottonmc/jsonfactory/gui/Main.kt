package io.github.cottonmc.jsonfactory.gui

import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.Gens
import io.github.cottonmc.jsonfactory.plugin.*
import picocli.CommandLine
import java.io.File
import java.util.Properties
import kotlin.reflect.KClass

@CommandLine.Command(name = "json-factory-gui", mixinStandardHelpOptions = true, versionProvider = Main.VersionProvider::class)
private object Main : Runnable {
    @CommandLine.Option(names = ["-p", "--plugin-classes"], description = ["list of plugin classes that will be loaded from the classpath"])
    var pluginClasses: Array<String> = emptyArray()

    override fun run() {
        Settings.init()
        // TODO: Show a splash screen or something during plugin loading
        Gui.createAndShow(loadGens(pluginClasses))
    }

    internal class VersionProvider : CommandLine.IVersionProvider {
        override fun getVersion(): Array<String> {
            val properties = Properties()
            properties.load(VersionProvider::class.java.getResourceAsStream("/json-factory/version-info.properties"))
            return arrayOf("JSON Factory GUI " + properties["version"] + " (git commit ${properties["commit-hash"]})")
        }
    }
}

fun main(args: Array<String>) = CommandLine.run(Main, *args)

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
