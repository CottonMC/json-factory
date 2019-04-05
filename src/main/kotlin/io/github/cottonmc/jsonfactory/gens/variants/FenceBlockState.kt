package io.github.cottonmc.jsonfactory.gens.variants

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.MultipartBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object FenceBlockState : ContentGenerator("Fence Block State", "blockstates", GeneratorInfo.FENCES) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            MultipartBlockState(
                listOf(
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_fence_post"))
                    ),
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_fence_side"), uvlock = true),
                        MultipartBlockState.When("north", "true")
                    ),
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_fence_side"), uvlock = true, y = 90),
                        MultipartBlockState.When("east", "true")
                    ),
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_fence_side"), uvlock = true, y = 180),
                        MultipartBlockState.When("south", "true")
                    ),
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_fence_side"), uvlock = true, y = 270),
                        MultipartBlockState.When("west", "true")
                    )
                )
            ),
        "fence")
    )
}
