package io.github.cottonmc.jsonfactory.gens.block.fencegate

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.Model
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object FenceGateBlockModel : ContentGenerator("Fence Gate Block Model", "models/block",
    GeneratorInfo.FENCE_GATES
) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(Model(
            parent = Identifier.mc("block/template_fence_gate"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "fence_gate"),
        Suffixed(Model(
            parent = Identifier.mc("block/template_fence_gate_wall"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "fence_gate_wall"),
        Suffixed(Model(
            parent = Identifier.mc("block/template_fence_gate_open"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "fence_gate_open"),
        Suffixed(Model(
            parent = Identifier.mc("block/template_fence_gate_wall_open"),
            textures = mapOf(
                "texture" to id.prefixPath("block/")
            )
        ), "fence_gate_wall_open")
    )
}
