package io.github.cottonmc.jsonfactory.gui.util

import io.github.cottonmc.jsonfactory.frontend.i18n.CombinedI18n
import io.github.cottonmc.jsonfactory.frontend.i18n.I18n
import io.github.cottonmc.jsonfactory.frontend.i18n.ResourceBundleI18n

object I18n : I18n by CombinedI18n(
    primary = ResourceBundleI18n("json-factory.i18n.I18n-gui"),
    secondary = ResourceBundleI18n.createBackendI18n()
)
