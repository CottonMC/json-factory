package io.github.cottonmc.jsonfactory.output

import io.github.cottonmc.jsonfactory.data.StringWrapper

/**
 * Combines an [Output] and a [nameWrapper] into an `Output` with a name wrapper.
 */
private class WrappedOutput(val delegate: Output, override val nameWrapper: StringWrapper) : Output by delegate

/**
 * Combines an [Output] and a [prefix] into an `Output` with a name prefix.
 * @since 0.4.0
 */
fun Output.prefixed(prefix: String): Output =
    WrappedOutput(if (this is WrappedOutput) delegate else this, nameWrapper.appendPrefix(prefix))

/**
 * Combines an [Output] and a [suffix] into an `Output` with a name suffix.
 * @since 0.4.0
 */
fun Output.suffixed(suffix: String): Output =
    WrappedOutput(if (this is WrappedOutput) delegate else this, nameWrapper.appendSuffix(suffix))
