package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Point

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

    data class Element(val from: Point, val to: Point, val faces: Map<String, Face>)
    data class Face(val texture: String, val cullface: String = "") : Json.ByProperties {
        override val properties = createProperties { self ->
            +self::texture
            +self::cullface.removeIfEmpty()
        }
    }
}
