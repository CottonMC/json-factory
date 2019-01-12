package io.github.cottonmc.jsonfactory.data.output

import io.github.cottonmc.jsonfactory.data.Serializer
import java.io.File

/**
 * A JSON output.
 */
interface Json : Output {
    override fun writeToFile(file: File) = file.writeText(Serializer.toJson(this))

    interface ByProperties : Json {
        val properties: List<Property<*>>
    }
}
