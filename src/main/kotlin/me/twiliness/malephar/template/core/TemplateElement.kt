package me.twiliness.malephar.template.core

import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import java.awt.Graphics2D
import java.awt.image.BufferedImage

interface TemplateElement {
    fun render(image : BufferedImage, graphics : Graphics2D, boundRect: BoundRect, context: TemplateContext, debug : Boolean)

    fun measure(image : BufferedImage, graphics : Graphics2D, parentRect: BoundRect, context: TemplateContext) : Area
}