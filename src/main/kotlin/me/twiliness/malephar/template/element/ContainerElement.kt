package me.twiliness.malephar.template.element

import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.template.core.TemplateElementBuilder
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import me.twiliness.malephar.engine.util.toArea
import me.twiliness.malephar.template.option.Overflow
import me.twiliness.malephar.template.option.Padding
import me.twiliness.malephar.engine.util.Tools
import java.awt.Graphics2D
import java.awt.image.BufferedImage

open class ContainerElement(
    child: TemplateElement,
    val padding: Padding,
    val overflow: Overflow
) : SingleChildElement(child) {

    override fun render(image : BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext, debug: Boolean) {
        if (overflow == Overflow.HIDDEN)
            graphics.setClip(boundRect.x, boundRect.y, boundRect.width, boundRect.height)

        val bound = measurePadding(boundRect)

        child.render(image, graphics, bound, context, debug)

        if (debug)
            Tools.drawDebugRect(graphics, bound)


        if (overflow == Overflow.HIDDEN)
            graphics.clip = null
    }

    private fun measurePadding(boundRect: BoundRect): BoundRect {
        val nx = boundRect.x + padding.left
        val ny = boundRect.y + padding.top

        val nw = boundRect.width - padding.right - padding.left
        val nh = boundRect.height - padding.bottom - padding.top

        return BoundRect(nx, ny, nw, nh)
    }

    override fun measure(image : BufferedImage, graphics: Graphics2D, parentRect: BoundRect, context: TemplateContext): Area {
        return parentRect.toArea()
    }

    class Builder : TemplateElementBuilder<ContainerElement> {
        lateinit var child: TemplateElement

        var padding: Padding = Padding.all(0)
        var overflow: Overflow = Overflow.DISPLAY

        override fun build(): ContainerElement {
            return ContainerElement(
                child,
                padding,
                overflow
            )
        }
    }
}