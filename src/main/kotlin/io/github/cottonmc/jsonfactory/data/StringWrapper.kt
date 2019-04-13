package io.github.cottonmc.jsonfactory.data

/**
 * `StringWrapper`'s contents can be used to wrap a string with a [prefix] and a [suffix].
 * The [separator] will be used to separate the prefix/suffix from the main string.
 *
 * @since 0.4.0
 */
data class StringWrapper(val prefix: String = "", val suffix: String = "", val separator: String = "_") {
    /**
     * Applies this wrapper's [prefix] and [suffix] to the [str].
     */
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

    fun appendPrefix(prefix: String) =
        if (this.prefix == "") copy(prefix = prefix)
        else copy(prefix = this.prefix + separator + prefix)

    fun appendSuffix(suffix: String) =
        if (this.suffix == "") copy(suffix = suffix)
        else copy(suffix = this.suffix + separator + suffix)
}
