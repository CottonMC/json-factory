package io.github.cottonmc.jsonfactory.data.types

import io.github.cottonmc.jsonfactory.data.Json

data class Model(
    val parent: String? = null,
    val textures: Map<String, String> = emptyMap(),
    val elements: List<Element> = emptyList()
) : Json.ByMap {
    // TODO
    data class Element(val from: List<Int>, val to: List<Int>)

    override fun toMap() = mutableMapOf(
        "parent" to parent,
        "textures" to textures
    ).also {
        if (elements.isNotEmpty())
            it["elements"] = elements.map {
                mapOf(
                    "from" to it.from,
                    "to" to it.to
                )
            }
    }
}
