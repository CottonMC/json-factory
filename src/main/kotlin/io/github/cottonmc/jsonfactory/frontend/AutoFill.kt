package io.github.cottonmc.jsonfactory.frontend

/**
 * An input string that a user can auto-fill in a frontend from a menu, for example.
 *
 * @property translationKey the translation key
 * @property value the filled value, usually a comma-separated list of [identifiers][io.github.cottonmc.jsonfactory.data.Identifier]
 */
data class AutoFill(val translationKey: String, val value: String)
