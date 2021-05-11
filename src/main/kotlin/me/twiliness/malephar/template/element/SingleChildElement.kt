package me.twiliness.malephar.template.element

import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import java.awt.Graphics2D
import java.awt.image.BufferedImage

open class SingleChildElement(val child: TemplateElement) : TemplateElement {
    override fun render(image : BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext, debug: Boolean) {
        child.render(image, graphics, boundRect, context, debug)
    }

    override fun measure(image : BufferedImage, graphics: Graphics2D, parentRect: BoundRect, context: TemplateContext): Area {
        return child.measure(image, graphics, parentRect, context)
    }
}