package io.github.cottonmc.jsonfactory.output

/**
 * Combines an [Output] and a [suffix] into an `Output` with a suffix.
 */
class Suffixed(delegate: Output, override val suffix: String) : Output by delegate
