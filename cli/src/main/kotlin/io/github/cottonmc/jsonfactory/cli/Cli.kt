package io.github.cottonmc.jsonfactory.cli

import io.github.cottonmc.jsonfactory.frontend.Frontend
import io.github.cottonmc.jsonfactory.frontend.MessageType
import io.github.cottonmc.jsonfactory.frontend.i18n.LayeredI18n
import io.github.cottonmc.jsonfactory.frontend.i18n.ResourceBundleI18n
import io.github.cottonmc.jsonfactory.frontend.i18n.invoke
import io.github.cottonmc.jsonfactory.frontend.plugin.*
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.reflect.KClass

internal class Cli(val outputDirectory: Path?, val pluginClasses: Set<String>) : Frontend {
    @Suppress("UNCHECKED_CAST")
    val plugins: List<Plugin> = sequence {
        yieldAll(ClasspathPluginLoader(pluginClasses.map { Class.forName(it).kotlin as KClass<out Plugin> }).loadRecursively())
        yieldAll(JarPluginLoader(Paths.get("plugins")).loadRecursively())
    }.toList()

    val i18n = LayeredI18n(
        ResourceBundleI18n.createBackendI18n(),
        setOf(ResourceBundleI18n("json-factory.i18n.I18n-cli"), *plugins.mapNotNull(Plugin::i18n).toTypedArray())
    )

    override fun printMessage(msg: String, type: MessageType, vararg messageParameters: Any?) = when (type) {
        // TODO: I18n
        MessageType.Warn, MessageType.Error -> System.err.println(i18n(msg, messageParameters))
        else -> println(i18n(msg, messageParameters))
    }

    override fun printSeparator() {}
    override fun onFinishedGenerating() {}
    override suspend fun shouldOverwriteFile(path: Path) = false
    override suspend fun selectOutputDirectory(): Path = outputDirectory!!
}
