package io.github.cottonmc.jsonfactory.util

import com.google.gson.GsonBuilder
import io.github.cottonmc.jsonfactory.output.Json
import io.github.cottonmc.jsonfactory.output.Property

/**
 * Serializes objects.
 */
// TODO: Remove
@Deprecated("Will be made internal or removed in a future release")
object Serializer {
    private val gson = GsonBuilder()
        .registerTypeHierarchyAdapter(Json.ByProperties::class.java, Property)
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .create()

    /**
     * Converts an [obj] to a JSON string.
     */
    fun toJson(obj: Any): String =
        gson.toJson(obj)
}
