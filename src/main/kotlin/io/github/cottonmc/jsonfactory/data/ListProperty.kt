package io.github.cottonmc.jsonfactory.data

data class ListProperty(val name: String, val values: List<String>) {
    companion object {
        val axis = ListProperty("axis", listOf("x", "y", "z"))
        val horizontalFacing = ListProperty("facing", listOf("north", "east", "south", "west"))
        val half = ListProperty("half", listOf("bottom", "top"))
        val halfUL = ListProperty("half", listOf("upper", "lower"))
        val slabType = ListProperty("type", listOf("bottom", "top", "double"))
        val stairShape =
            ListProperty("shape", listOf("straight", "outer_right", "outer_left", "inner_right", "inner_left"))
        val buttonFace = ListProperty("face", listOf("floor", "wall", "ceiling"))
        val powered = boolean("powered")
        val gateInWall = boolean("in_wall")
        val open = boolean("open")
        val doorHinge = ListProperty("hinge", listOf("left", "right"))
        val bottom = ListProperty("bottom", listOf("true", "false"))

        fun boolean(name: String) = ListProperty(name, listOf("true", "false"))
    }
}
