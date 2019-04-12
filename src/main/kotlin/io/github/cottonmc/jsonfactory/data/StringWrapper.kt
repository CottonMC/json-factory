package io.github.cottonmc.jsonfactory.data

/**
 * `StringWrapper`'s contents can be used to wrap a string with a [prefix] and a [suffix].
 * The [separator] will be used to separate the prefix/suffix from the main string.
 */
data class StringWrapper(val prefix: String = "", val suffix: String = "", val separator: String = "_") {
    fun applyTo(str: String) = buildString {
        if (prefix != "") {
            append(prefix)
            append(separator)
        }

        append(str)

        if (suffix != "") {
            append(separator)
            append(suffix)
        }
    }
}
