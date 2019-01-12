package io.github.cottonmc.jsonfactory.data.types

import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Json

data class LootTable(private val id: Identifier, private val isSlab: Boolean = false) : Json {
    override fun toJson() = JsonObject(mapOf(
        "type" to "minecraft:block",
        "pools" to JsonArray(listOf(
            JsonObject(
                mapOf(
                    "rolls" to 1,
                    "entries" to JsonArray(listOf(JsonObject(mapOf(
                        "type" to "minecraft:item",
                        "name" to id.toString()
                    ).let {
                        if (isSlab) it + slabEntry
                        else it
                    }))),
                    "conditions" to JsonArray(listOf(JsonObject(mapOf(
                        "condition" to "minecraft:survives_explosion"
                    ))))
                )
            )
        ))
    ))

    private val slabEntry = mapOf(
        "functions" to JsonArray(listOf(JsonObject(mapOf(
            "function" to "minecraft:set_count",
            "conditions" to JsonArray(listOf(JsonObject(mapOf(
                "condition" to "minecraft:block_state_property",
                "block" to id.toString(),
                "properties" to JsonObject(mapOf(
                    "type" to "double"
                ))
            )))),
            "count" to 2
        ))))
    )
}
