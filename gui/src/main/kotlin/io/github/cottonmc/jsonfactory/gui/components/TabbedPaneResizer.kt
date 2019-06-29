package io.github.cottonmc.jsonfactory.gui.components

import java.awt.Component
import javax.swing.JPanel
import javax.swing.JTabbedPane

/**
 * Attaching a resizer to a [tabbed pane][pane] will cause it to
 * resize when the user selects different tabs, basically setting the size
 * to the minimum size instead of the size of the largest tab.
 */
// https://stackoverflow.com/a/2522442
internal class TabbedPaneResizer(private val pane: JTabbedPane) {
    private val components: List<Component> = List(pane.tabCount, pane::getComponentAt)
    private val emptyPanels: List<JPanel> = List(components.size) { JPanel() }

    init {
        for (i in 0 until pane.tabCount) {
            if (i != pane.selectedIndex) {
                pane.setComponentAt(i, emptyPanels[i])
            }
        }

        pane.addChangeListener {
            for (i in 0 until pane.tabCount) {
                if (i != pane.selectedIndex) {
                    pane.setComponentAt(i, emptyPanels[i])
                } else {
                    pane.setComponentAt(i, components[i])
                }
            }
        }
    }
}
