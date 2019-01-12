package io.github.cottonmc.jsonfactory.data.output

import io.github.cottonmc.jsonfactory.data.Identifier

data class LootTable(private val id: Identifier) : Json.ByMap {
    override fun toMap() = mapOf(
        "type" to "minecraft:block",
        "pools" to listOf(
            Pool(
                listOf(Entry(id)),
                listOf(Condition(Identifier.mc("survives_explosion")))
            )
        )
    )

    data class Condition(val condition: Identifier)
    sealed class Function(
        val function: Identifier,
        val conditions: List<Condition> = emptyList()
    )

    data class Entry(
        val name: Identifier,
        val type: Identifier = Identifier.mc("item"),
        val conditions: List<Condition> = emptyList(),
        val functions: List<Function> = emptyList()
    )

    data class Pool(
        val entries: List<Entry>,
        val conditions: List<Condition> = emptyList(),
        val rolls: Int = 1
    )
}
