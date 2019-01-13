package io.github.cottonmc.jsonfactory.data

data class ListProperty(val name: String, val values: List<String>) {
    companion object {
        val axis = ListProperty("axis", listOf("x", "y", "z"))
        val horizontalFacing = ListProperty("facing", listOf("north", "east", "south", "west"))
        val half = ListProperty("half", listOf("bottom", "top"))
        val slabType = ListProperty("type", listOf("bottom", "top", "double"))
        val stairShape = ListProperty("shape", listOf("straight", "outer_right", "outer_left", "inner_right", "inner_left"))
        val buttonFace = ListProperty("face", listOf("floor", "wall", "ceiling"))
        val powered = ListProperty("powered", listOf("true", "false"))
    }
}
