package io.github.cottonmc.jsonfactory.data.types

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import io.github.cottonmc.jsonfactory.data.Json

data class Model(
    val parent: String? = null,
    val textures: Map<String, String> = emptyMap(),
    val elements: List<Element> = emptyList()
) : Json {
    // TODO
    data class Element(val from: List<Int>, val to: List<Int>) {}

    override fun toJson() = JsonObject(mutableMapOf(
        "parent" to parent,
        "textures" to JsonObject(textures)
    ).also {
        if (elements.isNotEmpty())
            it["elements"] = JsonArray(elements.map {
                JsonObject(
                    mapOf(
                        "from" to it.from,
                        "to" to it.to
                    )
                )
            })
    })
}
