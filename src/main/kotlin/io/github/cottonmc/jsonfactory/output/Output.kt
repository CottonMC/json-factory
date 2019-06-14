package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.StringWrapper
import java.io.File
import java.io.OutputStream

/**
 * A type that can write itself to a file.
 */
interface Output {
    /**
     * The file name wrapper, used to optionally wrap the file name with a prefix and/or a suffix.
     */
    val nameWrapper: StringWrapper get() = StringWrapper()

    /**
     * Writes this object to the [file].
     */
    @Deprecated("Use writeToStream instead", ReplaceWith("writeToStream(file.outputStream())"))
    fun writeToFile(file: File): Unit =
        writeToStream(file.outputStream())

    /**
     * Writes this object to the [stream].
     * @since 0.3.3
     */
    fun writeToStream(stream: OutputStream)
}
