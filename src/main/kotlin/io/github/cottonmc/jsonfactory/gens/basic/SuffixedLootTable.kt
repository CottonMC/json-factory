package io.github.cottonmc.jsonfactory.gens.basic

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Suffixed
import io.github.cottonmc.jsonfactory.output.loot.Condition
import io.github.cottonmc.jsonfactory.output.loot.Entry
import io.github.cottonmc.jsonfactory.output.loot.LootTable
import io.github.cottonmc.jsonfactory.output.loot.Pool

class SuffixedLootTable(display: String, private val suffix: String, info: GeneratorInfo) : ContentGenerator("$display Loot Table", "loot_tables/blocks",
    info, resourceRoot = ResourceRoot.Data
) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(LootTable(
            pools = listOf(
                Pool(
                    entries = listOf(
                        Entry(
                            id.suffixPath("_$suffix")
                        )
                    ),
                    conditions = listOf(
                        Condition(
                            condition = Identifier.mc("survives_explosion")
                        )
                    )
                )
            )
        ), suffix)
    )
}
