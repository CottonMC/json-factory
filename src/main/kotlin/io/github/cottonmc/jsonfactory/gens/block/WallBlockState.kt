package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.ContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelBlockState
import io.github.cottonmc.jsonfactory.output.model.MultipartBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

internal object WallBlockState : ContentGenerator("Wall Block State", "blockstates", GeneratorInfo.WALLS) {
    override fun generate(id: Identifier) = listOf(
        Suffixed(
            MultipartBlockState(
                listOf(
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_wall_post")),
                        MultipartBlockState.When("up", "true")
                    ),
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_wall_side"), uvlock = true),
                        MultipartBlockState.When("north", "true")
                    ),
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_wall_side"), uvlock = true, y = 90),
                        MultipartBlockState.When("east", "true")
                    ),
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_wall_side"), uvlock = true, y = 180),
                        MultipartBlockState.When("south", "true")
                    ),
                    MultipartBlockState.Multipart(
                        ModelBlockState.Variant(id.copy(path = "block/${id.path}_wall_side"), uvlock = true, y = 270),
                        MultipartBlockState.When("west", "true")
                    )
                )
            ),
        "wall")
    )
}
