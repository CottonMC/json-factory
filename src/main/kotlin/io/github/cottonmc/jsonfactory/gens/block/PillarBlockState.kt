package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.ListProperty
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.ModelBlockState

internal object PillarBlockState : ContentGenerator("Pillar Block State", "blockstates", GeneratorInfo.PILLARS) {
    override fun generate(id: Identifier) = listOf(
        ModelBlockState.create(id, listOf(ListProperty.axis)) { values, variant ->
            val axis = values["axis"]!!

            variant.copy(
                x = if (axis != "y") 90 else 0,
                y = if (axis == "x") 90 else 0
            )
        }
    )
}
