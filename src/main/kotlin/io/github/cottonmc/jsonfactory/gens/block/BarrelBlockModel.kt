package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object BarrelBlockModel :
    AbstractContentGenerator("barrel.block_model", "models/block", GeneratorInfo.BARRELS) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/cube_bottom_top"),
            textures = mapOf(
                "side" to id.prefixPath("block/"),
                "top" to id.copy(path = "block/${id.path}_top"),
                "bottom" to id.copy(path = "block/${id.path}_bottom")
            )
        ).suffixed("barrel"),
        Model(
            parent = Identifier.mc("block/cube_bottom_top"),
            textures = mapOf(
                "side" to id.prefixPath("block/"),
                "top" to id.copy(path = "block/${id.path}_top_open"),
                "bottom" to id.copy(path = "block/${id.path}_bottom")
            )
        ).suffixed("barrel_open")
    )
}
