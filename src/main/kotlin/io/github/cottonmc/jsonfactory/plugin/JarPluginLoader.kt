package io.github.cottonmc.jsonfactory.plugin

import com.google.gson.Gson
import java.io.File
import java.net.URLClassLoader
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.isSubclassOf

class JarPluginLoader(private val directory: String = "plugins") : PluginLoader {
    private val gson = Gson()

    override fun loadPlugins(context: PluginLoadingContext): List<PluginContainer> {
        val pluginDir = context.runDirectory.resolve(directory)
        if (!pluginDir.exists()) {
            return emptyList()
        }

        require(pluginDir.isDirectory) {
            "Plugin directory must be a directory"
        }

        return pluginDir.listFiles {
            file: File -> file.extension.equals("jar", ignoreCase = true)
        }.flatMap { file: File ->
            val classLoader = URLClassLoader(arrayOf(file.toURI().toURL()), JarPluginLoader::class.java.classLoader)
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
