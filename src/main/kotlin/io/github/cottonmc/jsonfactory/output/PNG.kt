package io.github.cottonmc.jsonfactory.output

import java.awt.image.BufferedImage
import java.io.File
import java.io.OutputStream
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

    override fun writeToStream(stream: OutputStream) {
        ImageIO.write(image, "PNG", stream)
    }
}
