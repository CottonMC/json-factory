package io.github.cottonmc.jsonfactory.output.loot

import io.github.cottonmc.jsonfactory.output.Json
import io.github.cottonmc.jsonfactory.output.createProperties

data class Pool(
    val entries: List<Entry>,
    val conditions: List<Condition> = emptyList(),
    val rolls: Int = 1
) : Json.ByProperties {
    override val properties = createProperties { self ->
        +self::rolls
        +self::entries
        +self::conditions.removeIfEmpty()
    }
}
