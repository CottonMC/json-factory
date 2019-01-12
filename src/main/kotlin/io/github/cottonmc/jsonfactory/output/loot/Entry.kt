package io.github.cottonmc.jsonfactory.output.loot

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Json
import io.github.cottonmc.jsonfactory.output.createProperties

data class Entry(
    val name: Identifier,
    val type: Identifier = Identifier.mc(
        "item"
    ),
    val conditions: List<Condition> = emptyList(),
    val functions: List<Function> = emptyList()
) : Json.ByProperties {
    override val properties = createProperties { self ->
        +self::type
        +self::name
        +self::conditions.removeIfEmpty()
        +self::functions.removeIfEmpty()
    }
}
