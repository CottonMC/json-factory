package io.github.cottonmc.jsonfactory.data.output.loot

import io.github.cottonmc.jsonfactory.data.types.Identifier

open class Condition(val condition: Identifier) {
    class BlockStateProperty(val block: Identifier, val properties: Map<String, Any>) :
        Condition(
            Identifier.mc(
                "block_state_property"
            )
        )
}
