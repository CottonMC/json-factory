package io.github.cottonmc.jsonfactory.gui.components.dsl

import io.github.cottonmc.jsonfactory.gui.components.translatable.JFCheckBoxMenuItem
import io.github.cottonmc.jsonfactory.gui.components.translatable.JFMenu
import io.github.cottonmc.jsonfactory.gui.components.translatable.JFMenuItem
import io.github.cottonmc.jsonfactory.gui.components.translatable.JFRadioButtonMenuItem
import javax.swing.JMenu
import javax.swing.JMenuBar

@DslMarker
@Retention(AnnotationRetention.SOURCE)
annotation class MenuDsl

@MenuDsl
internal inline fun menuBar(block: JMenuBar.() -> Unit) = JMenuBar().apply(block)

@MenuDsl
internal inline fun JMenuBar.menu(i18nKey: String, block: JFMenu.() -> Unit) =
    JFMenu(i18nKey).apply(block)
        .also { add(it) }

@MenuDsl
internal inline fun JMenu.menu(i18nKey: String, block: JFMenu.() -> Unit) =
    JFMenu(i18nKey).apply(block)
        .also { add(it) }

@MenuDsl
internal inline fun JMenu.item(i18nKey: String, block: JFMenuItem.() -> Unit) =
    JFMenuItem(i18nKey).apply(block)
        .also { add(it) }

@MenuDsl
internal inline fun JMenu.radioButtonItem(i18nKey: String, block: JFRadioButtonMenuItem.() -> Unit) =
    JFRadioButtonMenuItem(i18nKey).apply(block)
        .also { add(it) }

@MenuDsl
internal inline fun JMenu.checkBoxItem(i18nKey: String, block: JFCheckBoxMenuItem.() -> Unit) =
    JFCheckBoxMenuItem(i18nKey).apply(block)
        .also { add(it) }
