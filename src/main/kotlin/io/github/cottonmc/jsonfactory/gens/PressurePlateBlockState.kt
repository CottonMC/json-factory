package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

object PressurePlateBlockState : ContentGenerator("Pressure Plate Block State", "blockstates", Categories.BlockVariants) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(ModelBlockState(mapOf(
            "powered=false" to ModelBlockState.Variant(id.copy(path = "block/${id.path}_pressure_plate")),
            "powered=false" to ModelBlockState.Variant(id.copy(path = "block/${id.path}_pressure_plate_down"))
        )), "pressure_plate")
    )
}
