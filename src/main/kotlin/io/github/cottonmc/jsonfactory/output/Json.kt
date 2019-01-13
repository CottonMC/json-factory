package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.util.Serializer
import java.io.File

/**
 * A JSON output.
 */
interface Json : Output {
    override fun writeToFile(file: File) = file.writeText(Serializer.toJson(this))

    /**
     * A JSON output that is generated from the [properties].
     */
    interface ByProperties : Json {
        val properties: List<Property<*>>
    }
}
