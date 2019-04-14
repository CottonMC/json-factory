package io.github.cottonmc.jsonfactory.gens

import io.github.cottonmc.jsonfactory.data.Identifier
import io.github.cottonmc.jsonfactory.output.PNG
import java.awt.Color
import java.awt.image.BufferedImage
import java.util.concurrent.ThreadLocalRandom

class PlaceholderTexture(info: GeneratorInfo) : ContentGenerator(
    "Placeholder ${info.category.displayName} Texture", "textures/${info.category.path}", info, extension = "png"
) {
    override fun generate(id: Identifier) = listOf(run {
        val color1 = randomColor()
        val color2 = color1.withDifferentHue()
        val image = BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB)
        val graphics = image.createGraphics()

        graphics.color = color1
        graphics.fillRect(0, 0, 8, 8)
        graphics.fillRect(8, 8, 8, 8)

        graphics.color = color2
        graphics.fillRect(8, 0, 8, 8)
        graphics.fillRect(0, 8, 8, 8)
        graphics.dispose()

        PNG(image)
    })

    private fun randomColor(): Color {
        val random = ThreadLocalRandom.current()
        var hue = random.nextFloat()

        val offsetRange = 0.025f
        val hueOffset = random.nextFloat() * offsetRange - offsetRange / 2
        hue += hueOffset

        if (hue < 0) hue += 1f
        if (hue > 1) hue -= 1f

        // Saturation between 0.1 and 0.3
        val saturation = (random.nextInt(800) + 7200) / 10000f
        val luminance = (random.nextInt(200) + 8300) / 10000f
        return Color.getHSBColor(hue, saturation, luminance)
    }

    private fun Color.withDifferentHue(): Color {
        val hsb = Color.RGBtoHSB(red, green, blue, null)
        return Color.getHSBColor(
            (hsb[0] + ThreadLocalRandom.current().nextDouble(0.2, 0.8).toFloat()) % 1f,
            hsb[1],
            hsb[2]
        )
    }
}
