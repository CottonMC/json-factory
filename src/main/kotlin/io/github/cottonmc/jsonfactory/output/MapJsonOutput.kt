package io.github.cottonmc.jsonfactory.output

/**
 * A marker interface for the experimental [MapJsonOutput] class.
 * @since 0.5.0
 */
@Experimental
annotation class ExperimentalMapOutput

/**
 * An [Output] type that takes in a [map] and produces a JSON object.
 * @since 0.5.0
 */
@ExperimentalMapOutput
class MapJsonOutput(private val map: Map<*, *>) : Json {
    override fun toJsonString() = Json.toJson(map)
}
