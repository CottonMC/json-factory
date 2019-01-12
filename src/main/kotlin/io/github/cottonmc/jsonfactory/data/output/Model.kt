package io.github.cottonmc.jsonfactory.data.output

import io.github.cottonmc.jsonfactory.data.types.Identifier
import io.github.cottonmc.jsonfactory.data.types.Point

data class Model(
    val parent: Identifier? = null,
    val textures: Map<String, Any> = emptyMap(),
    val elements: List<Element> = emptyList()
) : Json.ByProperties {
    override val properties = Property.createList {
        val self = this@Model
        +self::parent
        +self::textures.removeIfEmpty()
        +self::elements.removeIfEmpty()
    }

    data class Element(val from: Point, val to: Point, val faces: Map<String, Face>)
    data class Face(val texture: String, val cullface: String = "") : Json.ByProperties {
        override val properties = Property.createList {
            val self = this@Face
            +self::texture
            +self::cullface.removeIfEmpty()
        }
    }
}
