package io.github.cottonmc.jsonfactory.data.output.loot

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.output.Json
import io.github.cottonmc.jsonfactory.data.output.Property

data class Entry(
    val name: Identifier,
    val type: Identifier = Identifier.mc(
        "item"
    ),
    val conditions: List<Condition> = emptyList(),
    val functions: List<Function> = emptyList()
) : Json.ByProperties {
    override val properties = Property.createList {
        val self = this@Entry
        +self::type
        +self::name
        +self::conditions.removeIfEmpty()
        +self::functions.removeIfEmpty()
    }
}
