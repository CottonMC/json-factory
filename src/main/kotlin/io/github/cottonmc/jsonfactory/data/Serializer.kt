package io.github.cottonmc.jsonfactory.data

import com.google.gson.GsonBuilder

/**
 * Converts a [Json] object to a JSON string.
 */
object Serializer {
    private val gson = GsonBuilder().apply {
        registerTypeAdapter(Identifier::class.java, Identifier)
    }.setPrettyPrinting().create()

    fun toJson(obj: Any): String =
        gson.toJson(obj)
}
