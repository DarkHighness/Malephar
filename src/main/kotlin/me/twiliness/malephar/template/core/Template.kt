package me.twiliness.malephar.template.core

import me.twiliness.malephar.template.element.LayerElement
import me.twiliness.malephar.engine.util.BoundRect
import java.awt.Graphics2D
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class Template(private val layers : List<LayerElement>,
               private val width: Int,
               private val height: Int,
               private val debug: Boolean) {

    private fun antialiasing(graphics2D: Graphics2D) {
        val hints =
            Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints") as Map<*, *>

        graphics2D.setRenderingHints(hints)
    }

    fun render(context : TemplateContext) {
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val canvas = image.createGraphics()
        val bound = BoundRect(0,0,width, height)

        antialiasing(canvas)

        for (layer in layers)
            this.renderLayer(image, layer, canvas, bound, context)

        ImageIO.write(image, "png", File("output.png"))

        canvas.dispose()
    }

    private fun renderLayer(image: BufferedImage, layerElement: LayerElement, canvas : Graphics2D, boundRect: BoundRect, context: TemplateContext) {
        layerElement.render(image, canvas, boundRect, context, debug)
    }
}

