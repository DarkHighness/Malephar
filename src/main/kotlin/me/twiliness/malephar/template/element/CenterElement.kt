package me.twiliness.malephar.template.element

import me.twiliness.malephar.template.core.TemplateElementBuilder
import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.BoundRect
import me.twiliness.malephar.engine.util.mkBound
import me.twiliness.malephar.engine.util.Tools
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class CenterElement(child: TemplateElement) : SingleChildElement(child) {
    private fun computeCenterBound(image: BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext): BoundRect {
        val childBound = child.measure(image, graphics, boundRect, context)

        return mkBound(
            x = boundRect.x + boundRect.width / 2 - childBound.width / 2,
            y = boundRect.y + boundRect.height / 2 - childBound.height / 2,
            width = childBound.width,
            height = childBound.height
        )
    }

    override fun render(image : BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext, debug: Boolean) {
        val bound = computeCenterBound(image, graphics, boundRect, context)

        if (debug)
            Tools.drawDebugRect(graphics, bound)

        child.render(
            image,
            graphics,
            bound,
            context,
            debug
        )
    }

    class Builder : TemplateElementBuilder<CenterElement> {
        lateinit var child: TemplateElement

        override fun build(): CenterElement {
            return CenterElement(child)
        }

    }
}