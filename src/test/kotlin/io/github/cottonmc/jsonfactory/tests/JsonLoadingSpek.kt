package io.github.cottonmc.jsonfactory.tests

import com.google.gson.Gson
import com.google.gson.JsonObject
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root

abstract class JsonLoadingSpek(root: Root.() -> Unit) : Spek(root) {
    companion object {
        private const val ROOT_PATH = "/io/github/cottonmc/jsonfactory/tests/"
        @JvmStatic
        internal val GSON = Gson()

        @JvmStatic
        internal fun loadJson(path: String): JsonObject = GSON.fromJson(
            JsonLoadingSpek::class.java.getResourceAsStream("$ROOT_PATH$path").reader(), JsonObject::class.java
        )
    }
}
