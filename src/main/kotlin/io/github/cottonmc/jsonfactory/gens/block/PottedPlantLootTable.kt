package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.gens.ResourceRoot
import io.github.cottonmc.jsonfactory.output.loot.Condition
import io.github.cottonmc.jsonfactory.output.loot.Entry
import io.github.cottonmc.jsonfactory.output.loot.LootTable
import io.github.cottonmc.jsonfactory.output.loot.Pool
import io.github.cottonmc.jsonfactory.output.prefixed

object PottedPlantLootTable : AbstractContentGenerator(
    "potted_plant.loot_table", "loot_tables/blocks", GeneratorInfo.POTTED_PLANTS,
    resourceRoot = ResourceRoot.Data
) {
    override fun generate(id: Identifier) = listOf(
        LootTable(
            pools = listOf(
                Pool(
                    entries = listOf(Entry(name = Identifier.mc("flower_pot"))),
                    conditions = listOf(Condition(Identifier.mc("survives_explosion")))
                ),
                Pool(
                    entries = listOf(Entry(name = id)),
                    conditions = listOf(Condition(Identifier.mc("survives_explosion")))
                )
            )
        ).prefixed("potted")
    )
}
