package io.github.cottonmc.jsonfactory.gui.api

import io.github.cottonmc.jsonfactory.frontend.plugin.Plugin
import io.github.cottonmc.jsonfactory.gui.api.theme.Theme

interface GuiPlugin : Plugin {
    val themes: Collection<Theme>
}
