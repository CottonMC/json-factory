package io.github.cottonmc.jsonfactory.data

import io.github.cottonmc.jsonfactory.data.ToJson

object Serializer {
    fun toJson(obj: ToJson): String =
        obj.toJson().toJsonString(prettyPrint = true)
}
