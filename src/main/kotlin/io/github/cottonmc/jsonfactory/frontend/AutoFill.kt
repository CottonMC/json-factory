package io.github.cottonmc.jsonfactory.frontend

/**
 * An input string that a user can auto-fill in a frontend from a menu, for example.
 *
 * @property i18nKey the translation key
 * @property value the filled value, usually a comma-separated list of [identifiers][io.github.cottonmc.jsonfactory.data.Identifier]
 */
data class AutoFill(val i18nKey: String, val value: String)
