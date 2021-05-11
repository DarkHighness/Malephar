package me.twiliness.malephar.engine.core

import me.twiliness.malephar.engine.TemplateContext
import me.twiliness.malephar.engine.api.*
import me.twiliness.malephar.engine.props.Overflow
import java.awt.Color
import java.awt.Graphics2D
import java.awt.Toolkit
import java.awt.image.BufferedImage

class LayerImpl(private val child: IComponent) : ILayer {
    fun createAndInitLayerState(layerConfig: LayerConfig?) : LayerState {
        val layerState =
            if (layerConfig == null)
                createLayerState()
            else
                createLayerState(layerConfig)

        return layerState
    }

    private fun drawLayerBackground(graphics: Graphics2D, width: Int, height: Int) {
        graphics.color = Color.white
        graphics.fillRect(0,0, width, height)
    }

    private fun antialiasing(graphics2D: Graphics2D) {
        val hints =
            Toolkit.getDefaultToolkit().getDesktopProperty("awt.font.desktophints") as Map<*, *>

        graphics2D.setRenderingHints(hints)
    }

    override fun render(width: Int, height: Int, context: TemplateContext, useWebDefault:Boolean, debug: Boolean): BufferedImage {
        val layerConfig = createLayerConfig()

        layerConfig.setUseWebDefaults(useWebDefault)
        layerConfig.setLogger { level, message ->
            println("[$level] $message")
        }

        val layerState = createAndInitLayerState(layerConfig)
        val canvasImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val graphics = canvasImage.createGraphics()

        antialiasing(graphics)
        drawLayerBackground(graphics, width, height)

        val childState = child.collect(canvasImage, graphics, layerState, context)

        layerState.pushRootState(childState)
        layerState.overflow = Overflow.HIDDEN
        layerState.calculateLayout(width.toFloat(), height.toFloat())
        layerState.print()

        child.render(canvasImage, graphics, childState, Anchor(0,0), context, debug)

        return canvasImage
    }
}