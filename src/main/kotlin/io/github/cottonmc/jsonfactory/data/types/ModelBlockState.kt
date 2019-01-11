package io.github.cottonmc.jsonfactory.data.types

import com.beust.klaxon.JsonObject
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Json

data class ModelBlockState(val variants: Map<String, Variant>) : Json {
    data class Variant(val model: Identifier)

    override fun toJson() = JsonObject(mapOf(
        "variants" to JsonObject(variants.mapValues { (_, value) ->
            JsonObject(
                mapOf(
                    "model" to value.model.toString()
                )
            )
        })
    ))
}
