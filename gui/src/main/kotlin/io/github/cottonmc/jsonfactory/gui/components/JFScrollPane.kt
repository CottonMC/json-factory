package io.github.cottonmc.jsonfactory.gui.components

import java.awt.Component
import javax.swing.JScrollPane

/**
 * Custom `JScrollPane` that has faster vertical scrolling.
 */
internal class JFScrollPane(view: Component) : JScrollPane(view) {
    init {
        verticalScrollBar.unitIncrement = 16
    }
}
