package io.github.cottonmc.jsonfactory.data.output

import io.github.cottonmc.jsonfactory.data.Serializer
import java.io.File

/**
 * A JSON output.
 */
interface Json : Output {
    override fun writeToFile(file: File) = file.writeText(Serializer.toJson(this))

    interface ByMap : Output {
        /**
         * Converts this object to a map.
         */
        fun toMap(): Map<String, Any?>
        override fun writeToFile(file: File) = file.writeText(
            Serializer.toJson(
                toMap()
            )
        )
    }

    interface ByProperties : Json {
        val properties: List<Property<*>>
    }
}
