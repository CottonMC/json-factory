package io.github.cottonmc.jsonfactory.output.loot

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.Json
import io.github.cottonmc.jsonfactory.output.Property

open class Function(
    val function: Identifier,
    val conditions: List<Condition> = emptyList(),
    additionalProperties: List<Property<*>> = emptyList()
) : Json.ByProperties {
    override val properties = Property.createList {
        val self = this@Function
        +self::function
        +self::conditions.removeIfEmpty()
    } + additionalProperties

    class SetCount(conditions: List<Condition>, count: Int) : Function(
        Identifier.mc("set_count"), conditions,
        Property.createList {
            +Property("count", count)
        }
    )
}
