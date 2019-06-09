package io.github.cottonmc.jsonfactory.gui.util

import io.github.cottonmc.jsonfactory.frontend.i18n.LayeredI18n
import io.github.cottonmc.jsonfactory.frontend.i18n.I18n
import io.github.cottonmc.jsonfactory.frontend.i18n.ResourceBundleI18n

val I18n = MutableLayeredI18n(arrayListOf(ResourceBundleI18n("json-factory.i18n.I18n-gui")))

class MutableLayeredI18n(private val layers: MutableList<I18n>) : I18n by LayeredI18n(
    primary = ResourceBundleI18n.createBackendI18n(),
    layers = layers
) {
    internal fun addLayer(layer: I18n) {
        layers += layer
    }
}
