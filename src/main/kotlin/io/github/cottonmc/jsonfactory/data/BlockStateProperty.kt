package io.github.cottonmc.jsonfactory.data

/**
 * A block state property.
 *
 * @property name the name
 * @property values a set of all values
 */
data class BlockStateProperty(val name: String, val values: Set<String>) {
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
        val axis = BlockStateProperty("axis", setOf("x", "y", "z"))
        val horizontalFacing = BlockStateProperty("facing", setOf("north", "east", "south", "west"))
        val halfTB = BlockStateProperty("half", setOf("bottom", "top"))
        val halfUL = BlockStateProperty("half", setOf("upper", "lower"))
        val slabType = BlockStateProperty("type", setOf("bottom", "top", "double"))
        val stairShape = BlockStateProperty("shape", setOf("straight", "outer_right", "outer_left", "inner_right", "inner_left"))
        val buttonFace = BlockStateProperty("face", setOf("floor", "wall", "ceiling"))
        val powered = boolean("powered")
        val gateInWall = boolean("in_wall")
        val open = boolean("open")
        val doorHinge = BlockStateProperty("hinge", setOf("left", "right"))
        val bottom = BlockStateProperty("bottom", setOf("true", "false"))
        val facing = BlockStateProperty("facing", setOf("north", "east", "south", "west", "up", "down"))
        val lit = boolean("lit")

        /**
         * Creates a [BlockStateProperty] with the [name] and the two boolean values: `true` and `false`.
         */
        fun boolean(name: String) = BlockStateProperty(name, setOf("true", "false"))
    }
}
