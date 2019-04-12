package io.github.cottonmc.jsonfactory.data

data class BlockStateProperty(val name: String, val values: List<String>) {
    companion object {
        val axis = BlockStateProperty("axis", listOf("x", "y", "z"))
        val horizontalFacing = BlockStateProperty("facing", listOf("north", "east", "south", "west"))
        val halfTB = BlockStateProperty("half", listOf("bottom", "top"))
        val halfUL = BlockStateProperty("half", listOf("upper", "lower"))
        val slabType = BlockStateProperty("type", listOf("bottom", "top", "double"))
        val stairShape = BlockStateProperty("shape", listOf("straight", "outer_right", "outer_left", "inner_right", "inner_left"))
        val buttonFace = BlockStateProperty("face", listOf("floor", "wall", "ceiling"))
        val powered = boolean("powered")
        val gateInWall = boolean("in_wall")
        val open = boolean("open")
        val doorHinge = BlockStateProperty("hinge", listOf("left", "right"))

        fun boolean(name: String) = BlockStateProperty(name, listOf("true", "false"))
    }
}
