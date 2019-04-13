package io.github.cottonmc.jsonfactory.data

import com.google.gson.*
import com.google.gson.annotations.JsonAdapter
import java.lang.reflect.Type

/**
 * A 3D point with the coordinates [x], [y] and [z].
 */
@JsonAdapter(Point.Companion::class)
data class Point(val x: Number, val y: Number, val z: Number) {
    companion object : JsonSerializer<Point> {
        override fun serialize(point: Point, typeOfSrc: Type?, context: JsonSerializationContext) =
            JsonArray(3).apply {
                add(point.x)
                add(point.y)
                add(point.z)
            }
    }
}
