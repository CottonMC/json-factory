package io.github.cottonmc.jsonfactory.gui.util

import io.github.cottonmc.jsonfactory.frontend.i18n.LayeredI18n
import io.github.cottonmc.jsonfactory.frontend.i18n.I18n
import io.github.cottonmc.jsonfactory.frontend.i18n.ResourceBundleI18n

object I18n : LayeredI18n(
    primary = ResourceBundleI18n.createBackendI18n(),
    layers = arrayListOf(ResourceBundleI18n("json-factory.i18n.I18n-gui"))
) {
    internal fun addLayer(i18n: I18n) {
        (layers as MutableList<I18n>) += i18n
    }
}
