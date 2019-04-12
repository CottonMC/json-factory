package io.github.cottonmc.jsonfactory.output

import java.io.File
import java.io.OutputStream

/**
 * A type that can write itself to a file.
 */
interface Output {
    // TODO: Add prefixes
    /**
     * The file name suffix (placed before the extension if not empty).
     */
    val suffix: String get() = ""

    /**
     * Writes this object to the [file].
     */
    fun writeToFile(file: File)

    /**
     * Writes this object to the [stream].
     * @since 0.3.3
     */
    fun writeToStream(stream: OutputStream)
}
