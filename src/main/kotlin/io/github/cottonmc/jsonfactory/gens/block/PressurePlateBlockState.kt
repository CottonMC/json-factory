package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.VariantBlockState
import io.github.cottonmc.jsonfactory.output.model.ModelVariant
import io.github.cottonmc.jsonfactory.output.suffixed

internal object PressurePlateBlockState : AbstractContentGenerator(
    "pressure_plate.block_state", "blockstates",
    GeneratorInfo.PRESSURE_PLATES
) {
    override fun generate(id: Identifier) = listOf(
        VariantBlockState(
            mapOf(
                "powered=false" to ModelVariant(id.copy(path = "block/${id.path}_pressure_plate")),
                "powered=true" to ModelVariant(id.copy(path = "block/${id.path}_pressure_plate_down"))
            )
        ).suffixed("pressure_plate")
    )
}
