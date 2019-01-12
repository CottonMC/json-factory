package io.github.cottonmc.jsonfactory.data.output

import io.github.cottonmc.jsonfactory.data.Identifier

// TODO Replace with actual data classes
data class LootTable(private val id: Identifier) : Json.ByMap {
    override fun toMap() = mapOf(
        "type" to "minecraft:block",
        "pools" to listOf(
            mapOf(
                "rolls" to 1,
                "entries" to listOf(
                    mapOf(
                        "type" to "minecraft:item",
                        "name" to id.toString()
                    )
                ),
                "conditions" to listOf(
                    mapOf(
                        "condition" to "minecraft:survives_explosion"
                    )
                )
            )
        )
    )
}
