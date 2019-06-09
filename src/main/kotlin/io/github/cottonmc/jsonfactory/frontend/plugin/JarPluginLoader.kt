package io.github.cottonmc.jsonfactory.frontend.plugin

import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path
import java.util.ServiceLoader

/**
 * Loads plugins from JAR files in a [directory][pluginDir].
 */
class JarPluginLoader(private val pluginDir: Path) : PluginLoader {
    override fun loadPlugins(): List<Plugin> {
        if (Files.notExists(pluginDir)) {
            return emptyList()
        }

        require(Files.isDirectory(pluginDir)) {
            "Plugin directory must be a directory"
        }

        return Files.newDirectoryStream(pluginDir).filter {
            path -> path.fileName.toString().endsWith(".jar", ignoreCase = true)
        }.flatMap { path: Path ->
            val classLoader = URLClassLoader(arrayOf(path.toUri().toURL()), JarPluginLoader::class.java.classLoader)
            ServiceLoader.load(Plugin::class.java, classLoader)
        }
    }
}
