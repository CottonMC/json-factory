package io.github.cottonmc.jsonfactory.gui

import org.jdesktop.swingx.JXTipOfTheDay
import org.jdesktop.swingx.tips.TipLoader
import java.util.*
import javax.swing.JFrame
import kotlin.random.Random

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
    fun show(frame: JFrame, settings: Settings, isStartup: Boolean) {
        tipOfTheDay.currentTip = Random.nextInt(tipOfTheDay.model.tipCount)
        tipOfTheDay.showDialog(frame, settings.createTipOfTheDayChoice(if (isStartup) null else true))
    }
}
