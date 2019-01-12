package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.ModelBlockState
import io.github.cottonmc.jsonfactory.output.Suffixed

object StairBlockState : ContentGenerator<ModelBlockState>("Stair Block State", "blockstates", Categories.BlockVariants) {
    override fun generate(id: Identifier) = listOf(Suffixed(ModelBlockState(
        HashMap<String, ModelBlockState.Variant>().apply {
            for (half in halves) {
                for (facing in facings) {
                    for (shape in shapes) {
                        this["facing=$facing,half=$half,shape=$shape"] = generateVariant(id, half, facing, shape)
                    }
                }
            }
        }
    ), "stairs"))

    private fun generateVariant(id: Identifier, half: String, facing: String, shape: String): ModelBlockState.Variant {
        val suffix = when {
            shape.startsWith("outer") -> "_outer"
            shape.startsWith("inner") -> "_inner"
            else -> ""
        }

        val x = if (half == "top") 180 else 0
        val y = when (facing) {
            "south" -> 90
            "west" -> 180
            "north" -> 270
            else -> 0
        }.let {
            if (shape.endsWith("left"))
                (it + 270) % 360
            else it
        }

        val uvlock = x != 0 || y != 0

        return ModelBlockState.Variant(
            id.copy(path = "block/${id.path}_stairs$suffix"),
            x, y, uvlock
        )
    }

    private val halves = listOf("bottom", "top")
    private val facings = listOf("east", "west", "south", "north")
    private val shapes = listOf("straight", "outer_right", "outer_left", "inner_right", "inner_left")
}
