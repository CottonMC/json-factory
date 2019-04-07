package io.github.cottonmc.jsonfactory.output.loot

import io.github.cottonmc.jsonfactory.data.Identifier

open class Condition(val condition: Identifier) {
    class BlockStateProperty(val block: Identifier, val properties: Map<String, Any>) :
        Condition(
            Identifier.mc(
                "block_state_property"
            )
        )

    /**
     * @since 0.4.0
     */
    class Inverted(val term: Condition) :
        Condition(
            Identifier.mc(
                "inverted"
            )
        )

    /**
     * @since 0.4.0
     */
    class Alternative(val terms: List<Condition>) :
        Condition(
            Identifier.mc(
                "alternative"
            )
        )

    /**
     * @since 0.4.0
     */
    class MatchTool(val predicate: Map<String, Any>) :
        Condition(
            Identifier.mc(
                "match_tool"
            )
        )

    /**
     * @since 0.4.0
     */
    class TableBonus(val enchantment: Identifier, val chances: List<Double>) :
        Condition(
            Identifier.mc(
                "table_bonus"
            )
        )
}
