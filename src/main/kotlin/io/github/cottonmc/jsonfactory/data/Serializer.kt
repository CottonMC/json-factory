package io.github.cottonmc.jsonfactory.data

import com.google.gson.GsonBuilder
import io.github.cottonmc.jsonfactory.data.output.Json
import io.github.cottonmc.jsonfactory.data.output.Property

/**
 * Converts an object to a JSON string.
 */
object Serializer {
    private val gson = GsonBuilder()
        .registerTypeHierarchyAdapter(Json.ByProperties::class.java, Property)
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .create()

    fun toJson(obj: Any): String =
        gson.toJson(obj)
}
