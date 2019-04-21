package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.loot.*
import io.github.cottonmc.jsonfactory.output.loot.Function
import io.github.cottonmc.jsonfactory.output.suffixed

internal object BarrelLootTable : ContentGenerator(
    "barrels.loot_table", "loot_tables/blocks",
    GeneratorInfo.BARRELS, resourceRoot = ResourceRoot.Data
) {
    override fun generate(id: Identifier) = listOf(
        LootTable(
            pools = listOf(
                Pool(
                    entries = listOf(
                        Entry(
                            name = id.suffixPath("_barrel"),
                            functions = listOf(
                                Function.CopyName(source = "block_entity")
                            )
                        )
                    ),
                    conditions = listOf(
                        Condition(
                            condition = Identifier.mc("survives_explosion")
                        )
                    )
                )
            )
        ).suffixed("barrel")
    )
}
