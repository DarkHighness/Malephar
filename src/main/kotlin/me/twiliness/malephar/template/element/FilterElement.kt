package me.twiliness.malephar.template.element

import com.jhlabs.image.AbstractBufferedImageOp
import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.template.core.TemplateElementBuilder
import me.twiliness.malephar.engine.util.BoundRect
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class FilterElement(child : TemplateElement, val op: AbstractBufferedImageOp) : SingleChildElement(child) {
    override fun render(image : BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext, debug: Boolean) {
        super.render(image, graphics, boundRect, context, debug)

        val subImage =
            image.getSubimage(boundRect.x, boundRect.y, boundRect.width, boundRect.height)

        op.filter(subImage, subImage)
    }

    class Builder : TemplateElementBuilder<FilterElement> {
        lateinit var child: TemplateElement
        lateinit var op : AbstractBufferedImageOp

        override fun build(): FilterElement {
            return FilterElement(child, op)
        }
    }
}