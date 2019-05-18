package io.github.cottonmc.jsonfactory.tests.util

import com.google.gson.Gson
import com.google.gson.JsonObject

internal object JsonLoader {
    private const val ROOT_PATH = "/io/github/cottonmc/jsonfactory/tests/"
    @JvmStatic
    val GSON = Gson()

    @JvmStatic
    fun loadJson(path: String): JsonObject = GSON.fromJson(
        JsonLoader::class.java.getResourceAsStream("$ROOT_PATH$path").reader(), JsonObject::class.java
    )
}
