package io.github.cottonmc.jsonfactory.gens.block.slab

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Suffixed
import io.github.cottonmc.jsonfactory.output.loot.*
import io.github.cottonmc.jsonfactory.output.loot.Function

internal object SlabLootTable : ContentGenerator("Slab Loot Table", "loot_tables/blocks",
    GeneratorInfo.SLABS, resourceRoot = ResourceRoot.Data
) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            LootTable(
                pools = listOf(
                    Pool(
                        entries = listOf(
                            Entry(
                                name = id.suffixPath("_slab"),
                                functions = listOf(
                                    Function(Identifier.mc("explosion_decay")),
                                    Function.SetCount(
                                        conditions = listOf(
                                            Condition.BlockStateProperty(
                                                id.suffixPath("_slab"),
                                                properties = mapOf(
                                                    "type" to "double"
                                                )
                                            )
                                        ),
                                        count = 2
                                    )
                                )
                            )
                        )
                    )
                )
            ), "slab")
    )
}
