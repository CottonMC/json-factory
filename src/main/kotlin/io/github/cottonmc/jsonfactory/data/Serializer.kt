package io.github.cottonmc.jsonfactory.data

/**
 * Converts a [Json] object to a JSON string.
 */
object Serializer {
    fun toJson(obj: Json): String =
        obj.toJson().toJsonString(prettyPrint = true)
}
