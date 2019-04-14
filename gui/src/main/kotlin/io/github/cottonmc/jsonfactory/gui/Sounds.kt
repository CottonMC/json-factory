package io.github.cottonmc.jsonfactory.gui

import java.io.FileNotFoundException
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.Clip
import javax.sound.sampled.LineEvent

internal object Sounds {
    // Sound by Headphaze
    // https://freesound.org/people/Headphaze/sounds/277032/
    val finished = load("finished")

    // Sound by original_sound
    // https://freesound.org/people/original_sound/sounds/366102/
    val confirm = load("confirm")

    private fun load(path: String): Clip {
        val url = Sounds::class.java.getResource("/json-factory/sounds/$path.wav")
            ?: throw FileNotFoundException("Sound clip $path not found")

        val stream = AudioSystem.getAudioInputStream(url)
        val clip = AudioSystem.getClip()
        clip.open(stream)
        clip.addLineListener {
            if (it.type == LineEvent.Type.STOP)
                clip.framePosition = 0
        }

        return clip
    }
}
