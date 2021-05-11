package me.twiliness.malephar.engine.api

import me.twiliness.malephar.engine.TemplateContext
import java.awt.image.BufferedImage

interface ILayer {
    fun render(width : Int, height: Int, context: TemplateContext, useWebDefault: Boolean, debug: Boolean) : BufferedImage
}