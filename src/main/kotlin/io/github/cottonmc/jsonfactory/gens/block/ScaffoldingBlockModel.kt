package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.Model
import io.github.cottonmc.jsonfactory.output.suffixed

internal object ScaffoldingBlockModel :
    AbstractContentGenerator("scaffolding.block_model", "models/block", GeneratorInfo.SCAFFOLDING) {
    override fun generate(id: Identifier) = listOf(
        Model(
            parent = Identifier.mc("block/scaffolding_stable"),
            textures = mapOf(
                "particle" to id.prefixPath("block/").suffixPath("_top"),
                "top" to id.prefixPath("block/").suffixPath("_top"),
                "side" to id.prefixPath("block/").suffixPath("_side"),
                "bottom" to id.prefixPath("block/").suffixPath("_bottom")
            )
        ).suffixed("stable_scaffolding"),
        Model(
            parent = Identifier.mc("block/scaffolding_unstable"),
            textures = mapOf(
                "particle" to id.prefixPath("block/").suffixPath("_top"),
                "top" to id.prefixPath("block/").suffixPath("_top"),
                "side" to id.prefixPath("block/").suffixPath("_side"),
                "bottom" to id.prefixPath("block/").suffixPath("_bottom")
            )
        ).suffixed("unstable_scaffolding")
    )
}