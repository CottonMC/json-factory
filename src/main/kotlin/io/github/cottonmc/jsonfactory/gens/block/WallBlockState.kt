package io.github.cottonmc.jsonfactory.gens.block

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.gens.AbstractContentGenerator
import io.github.cottonmc.jsonfactory.gens.GeneratorInfo
import io.github.cottonmc.jsonfactory.output.model.ModelVariant
import io.github.cottonmc.jsonfactory.output.model.MultipartBlockState
import io.github.cottonmc.jsonfactory.output.suffixed

internal object WallBlockState : AbstractContentGenerator("wall.block_state", "blockstates", GeneratorInfo.WALLS) {
    override fun generate(id: Identifier) = listOf(
        MultipartBlockState(
            listOf(
                MultipartBlockState.Multipart(
                    ModelVariant(id.copy(path = "block/${id.path}_wall_post")),
                    MultipartBlockState.When("up", "true")
                ),
                MultipartBlockState.Multipart(
                    ModelVariant(
                        id.copy(path = "block/${id.path}_wall_side"),
                        uvlock = true
                    ),
                    MultipartBlockState.When("north", "true")
                ),
                MultipartBlockState.Multipart(
                    ModelVariant(
                        id.copy(path = "block/${id.path}_wall_side"),
                        uvlock = true,
                        y = 90
                    ),
                    MultipartBlockState.When("east", "true")
                ),
                MultipartBlockState.Multipart(
                    ModelVariant(
                        id.copy(path = "block/${id.path}_wall_side"),
                        uvlock = true,
                        y = 180
                    ),
                    MultipartBlockState.When("south", "true")
                ),
                MultipartBlockState.Multipart(
                    ModelVariant(
                        id.copy(path = "block/${id.path}_wall_side"),
                        uvlock = true,
                        y = 270
                    ),
                    MultipartBlockState.When("west", "true")
                )
            )
        ).suffixed("wall")
    )
}
