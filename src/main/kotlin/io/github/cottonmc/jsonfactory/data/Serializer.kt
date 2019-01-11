package io.github.cottonmc.jsonfactory.data

object Serializer {
    fun toJson(obj: Json): String =
        obj.toJson().toJsonString(prettyPrint = true)
}
