package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Point

/**
 * A model.
 *
 * @property parent the parent model
 * @property textures the textures
 * @property elements the elements
 */
data class Model(
    val parent: Identifier? = null,
    val textures: Map<String, Any> = emptyMap(),
    val elements: List<Element> = emptyList()
) : Json.ByProperties {
    override val properties = createProperties { self ->
        +self::parent
        +self::textures.removeIfEmpty()
        +self::elements.removeIfEmpty()
    }

    /**
     * An element.
     *
     * @property from the starting point
     * @property to the ending point
     * @property faces the face settings
     */
    data class Element(val from: Point, val to: Point, val faces: Map<String, Face>)

    /**
     * A face.
     *
     * @property texture the texture variable
     * @property cullface the cullface (`""` if none)
     */
    data class Face(val texture: String, val cullface: String = "") : Json.ByProperties {
        override val properties = createProperties { self ->
            +self::texture
            +self::cullface.removeIfEmpty()
        }
    }
}
