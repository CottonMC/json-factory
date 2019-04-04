package io.github.cottonmc.jsonfactory.output

import com.google.gson.JsonArray
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.annotations.JsonAdapter
import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.data.Point
import java.lang.reflect.Type

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
    val elements: List<Element> = emptyList(),
    val ambientOcclusion: Boolean = true
) : Json.ByProperties {
    override val properties = createProperties { self ->
        +self::parent
        +self::textures.removeIfEmpty()
        +self::elements.removeIfEmpty()
        +Property("ambientocclusion", ambientOcclusion, Property.Mode.RemoveIfTrue)
    }

    /**
     * An element.
     *
     * @property from the starting point
     * @property to the ending point
     * @property faces the face settings
     */
    data class Element(val from: Point, val to: Point, val faces: Map<String, Face>, val shade: Boolean = true) : Json.ByProperties {
        override val properties = createProperties { self ->
            +self::from
            +self::to
            +self::faces
            +self::shade.removeIfTrue()
        }
    }

    /**
     * A face.
     *
     * @property texture the texture variable
     * @property cullface the cullface (`""` if none)
     */
    data class Face(val texture: String, val cullface: String = "", val uv: Uv? = null) : Json.ByProperties {
        override val properties = createProperties { self ->
            +self::texture
            +self::cullface.removeIfEmpty()
            +self::uv.removeIfNull()
        }
    }

    /**
     * A UV value for a face, in the form of `[x1, y1, x2, y2]`.
     *
     * @property x1 The first X coordinate
     * @property y1 The first Y coordinate
     * @property x2 The second X coordinate
     * @property y2 The second Y coordinate
     * @since 0.4.0
     */
    @JsonAdapter(Uv.Companion::class)
    data class Uv(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
        companion object : JsonSerializer<Uv> {
            override fun serialize(uv: Uv, typeOfSrc: Type?, context: JsonSerializationContext) =
                JsonArray(4).apply {
                    add(uv.x1)
                    add(uv.y1)
                    add(uv.x2)
                    add(uv.y2)
                }
        }
    }
}
