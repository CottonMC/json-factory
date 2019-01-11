package io.github.cottonmc.jsonfactory.data

abstract class ContentGenerator<out T : Output>(val displayName: String, val path: String, val extension: String = "json") {
    abstract fun generate(id: Identifier): T
    override fun toString() = displayName
}
