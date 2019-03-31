package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.util.Serializer
import java.io.File
import java.io.OutputStream

/**
 * A JSON output.
 */
interface Json : Output {
    override fun writeToFile(file: File) = file.writeText(Serializer.toJson(this))
    override fun writeToStream(stream: OutputStream) =
        stream.bufferedWriter().write(Serializer.toJson(this))

    /**
     * Converts this output to a JSON string.
     * @since 0.3.3
     */
    fun toJsonString(): String = Serializer.toJson(this)

    /**
     * A JSON output that is generated from the [properties].
     */
    interface ByProperties : Json {
        val properties: List<Property<*>>
    }
}
