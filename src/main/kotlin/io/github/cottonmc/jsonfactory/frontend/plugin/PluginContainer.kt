package io.github.cottonmc.jsonfactory.frontend.plugin

/**
 * A data class containing a [plugin], its [id] and a [version].
 */
// TODO: Is this needed?
data class PluginContainer(val plugin: Plugin, val id: String, val version: String)
