package io.github.cottonmc.jsonfactory.data

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * A PNG output type.
 *
 * @property image the image
 */
class PNG(private val image: BufferedImage) : Output {
    override fun writeToFile(file: File) {
        ImageIO.write(image, "PNG", file)
    }
}
