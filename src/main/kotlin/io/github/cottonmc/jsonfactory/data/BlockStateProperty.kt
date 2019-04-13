package io.github.cottonmc.jsonfactory.data

/**
 * A block state property.
 *
 * @property name the name
 * @property values a list of all values
 */
data class BlockStateProperty(val name: String, val values: List<String>) {
    override fun equals(other: Any?) =
        other is BlockStateProperty && other.name == name

    override fun hashCode() = name.hashCode()

    /**
     * A map of property values wrapping around the `delegate`.
     *
     * Also provides access to the property values through their names.
     *
     * @param delegate the delegate
     * @since 0.4.0
     */
    class ValueMap(delegate: Map<BlockStateProperty, String>) : Map<BlockStateProperty, String> by delegate {
        private val propertyNames = delegate.keys.map { it.name to it }.toMap()

        operator fun get(name: String) = this[propertyNames[name]]
    }

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

        /**
         * Creates a [BlockStateProperty] with the [name] and the two boolean values: `true` and `false`.
         */
        fun boolean(name: String) = BlockStateProperty(name, listOf("true", "false"))
    }
}
