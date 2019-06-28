package io.github.cottonmc.jsonfactory.gui.api.theme

import javax.swing.LookAndFeel
import javax.swing.UIManager

interface Theme {
    val name: String
    val lookAndFeel: LookAndFeelInitializer
    val group: Group

    interface Group {
        val name: String
    }

    enum class DefaultGroup : Group {
        Light, Dark
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
        fun laf(laf: () -> LookAndFeel): LookAndFeelInitializer =
            LookAndFeelInitializer.Lazy(laf)

        fun laf(className: String): LookAndFeelInitializer =
            LookAndFeelInitializer.FromClass(className)
    }
}
