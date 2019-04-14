package io.github.cottonmc.jsonfactory.gui

import org.jdesktop.swingx.JXTipOfTheDay
import org.jdesktop.swingx.tips.TipLoader
import java.util.*
import javax.swing.JFrame

internal object Tips {
    private val tipOfTheDay: JXTipOfTheDay

    init {
        val props = Properties()
        props.load(Tips::class.java.getResourceAsStream("/json-factory/tips.properties"))
        tipOfTheDay = JXTipOfTheDay(TipLoader.load(props))
    }

    /**
     * Shows the tip of the day dialog.
     */
    fun show(frame: JFrame, isStartup: Boolean) {
        tipOfTheDay.currentTip = (0 until tipOfTheDay.model.tipCount).random()
        tipOfTheDay.showDialog(frame, Settings.createTipOfTheDayChoice(if (isStartup) null else true))
    }
}
