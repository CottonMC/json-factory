package io.github.cottonmc.jsonfactory.gui.api.theme

import javax.swing.LookAndFeel
import javax.swing.UIManager

interface Theme {
    /**
     * The unique ID of this theme.
     */
    val id: String

    /**
     * The display name of this theme.
     */
    val name: String
    val lookAndFeel: LookAndFeelInitializer
    val group: Group

    interface Group {
        val translationKey: String
    }

    enum class DefaultGroup(override val translationKey: String) : Group {
        Light("gui.theme_group.light"), Dark("gui.theme_group.dark")
    }

    sealed class LookAndFeelInitializer {
        abstract fun apply()

        internal class FromClass(private val className: String) : LookAndFeelInitializer() {
            override fun apply() = UIManager.setLookAndFeel(className)
        }

        internal class Lazy(private val laf: () -> LookAndFeel) : LookAndFeelInitializer() {
            override fun apply() = UIManager.setLookAndFeel(laf())
        }
    }

    companion object {
        fun lookAndFeel(laf: () -> LookAndFeel): LookAndFeelInitializer =
            LookAndFeelInitializer.Lazy(laf)

        fun lookAndFeel(className: String): LookAndFeelInitializer =
            LookAndFeelInitializer.FromClass(className)
    }
}
