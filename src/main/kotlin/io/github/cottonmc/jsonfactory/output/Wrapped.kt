package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.StringWrapper

/**
 * Combines an [Output] and a [nameWrapper] into an `Output` with a name wrapper.
 */
class Wrapped(delegate: Output, override val nameWrapper: StringWrapper) : Output by delegate

fun Output.prefixed(prefix: String) = Wrapped(this, StringWrapper(prefix = prefix))
fun Output.suffixed(suffix: String) = Wrapped(this, StringWrapper(suffix = suffix))
