package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.loot.*
import io.github.cottonmc.jsonfactory.output.loot.Function
import io.github.cottonmc.jsonfactory.output.suffixed

internal object ChestLootTable : ContentGenerator(
    "Chest Loot Table",
    "loot_tables/blocks",
    GeneratorInfo.CHESTS,
    resourceRoot = ResourceRoot.Data
) {
    override fun generate(id: Identifier) = listOf(
        LootTable(
            pools = listOf(
                Pool(
                    entries = listOf(
                        Entry(
                            name = id.suffixPath("_chest"),
                            functions = listOf(
                                Function.CopyName(source = "block_entity")
                            )
                        )
                    ),
                    conditions = listOf(Condition(Identifier.mc("survives_explosion")))
                )
            )
        ).suffixed("chest")
    )
}
