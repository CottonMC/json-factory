package io.github.cottonmc.jsonfactory.data.types

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Json

data class LootTable(private val id: Identifier) : Json {
    override fun toJson() = JsonObject(mapOf(
        "type" to "minecraft:block",
        "pools" to JsonArray(listOf(
            JsonObject(
                mapOf(
                    "rolls" to 1,
                    "entries" to JsonArray(listOf(JsonObject(mapOf(
                        "type" to "minecraft:item",
                        "name" to id.toString()
                    )))),
                    "conditions" to JsonArray(listOf(JsonObject(mapOf(
                        "condition" to "minecraft:survives_explosion"
                    ))))
                )
            )
        ))
    ))
}
