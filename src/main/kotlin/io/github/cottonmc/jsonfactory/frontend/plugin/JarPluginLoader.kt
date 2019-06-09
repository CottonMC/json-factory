package io.github.cottonmc.jsonfactory.frontend.plugin

import com.google.gson.Gson
import java.io.File
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

/**
 * Loads plugins from JAR files in a [directory][pluginDir].
 */
// TODO: ServiceLoader?
class JarPluginLoader(private val pluginDir: Path) : PluginLoader {
    private val gson = Gson()

    override fun loadPlugins(): List<PluginContainer> {
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
            val optionalFmj = runCatching {
                gson.fromJson(classLoader.getResourceAsStream("fabric.mod.json").reader(), FabricModJson::class.java)
            }

            optionalFmj.getOrNull()?.let { fmj ->
                fmj.entrypoints["json-factory"]?.mapNotNull {
                    val clazz = Class.forName(it, true, classLoader).kotlin
                    if (clazz.isSubclassOf(Plugin::class)) {
                        PluginContainer(
                            (clazz.objectInstance ?: clazz.createInstance()) as Plugin,
                            fmj.id,
                            fmj.version
                        )
                    } else null
                }
            } ?: emptyList()
        }
    }

    // A tiny subset of a fabric.mod.json
    private class FabricModJson(val id: String, val version: String, val entrypoints: Map<String, List<String>>)
}
