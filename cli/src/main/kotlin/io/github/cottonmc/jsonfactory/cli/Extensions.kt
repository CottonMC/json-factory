package io.github.cottonmc.jsonfactory.cli

import io.github.cottonmc.jsonfactory.frontend.ContentWriter

// TODO: Make ContentWriter stateless
@Deprecated("see todo", ReplaceWith("this"))
fun ContentWriter.withAllEnabled() = apply {
    gens2Selections.keys.forEach {
        gens2Selections[it] = true
    }
}
