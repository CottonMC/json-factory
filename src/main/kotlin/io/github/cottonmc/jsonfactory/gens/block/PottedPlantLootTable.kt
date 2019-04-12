package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.loot.Condition
import io.github.cottonmc.jsonfactory.output.loot.Entry
import io.github.cottonmc.jsonfactory.output.loot.LootTable
import io.github.cottonmc.jsonfactory.output.loot.Pool
import io.github.cottonmc.jsonfactory.output.prefixed

object PottedPlantLootTable : ContentGenerator("Potted Plant Model", "loot_tables/blocks", GeneratorInfo.POTTED_PLANTS,
    resourceRoot = ResourceRoot.Data) {
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
