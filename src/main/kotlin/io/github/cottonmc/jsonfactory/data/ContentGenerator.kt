package io.github.cottonmc.jsonfactory.data

abstract class ContentGenerator<out T : ToJson>(val displayName: String) {
    abstract fun generate(id: Identifier): T

    override fun toString() = displayName
}
