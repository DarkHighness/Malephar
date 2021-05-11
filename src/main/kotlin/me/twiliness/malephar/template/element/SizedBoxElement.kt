package me.twiliness.malephar.template.element

import me.twiliness.malephar.template.option.Padding
import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import me.twiliness.malephar.template.option.BoxSizing
import me.twiliness.malephar.template.option.Overflow
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class SizedBoxElement(child : TemplateElement,
                      padding: Padding,
                      overflow: Overflow,
                      val area: Area,
                      val boxSizing: BoxSizing) : ContainerElement(child, padding, overflow) {

    override fun render(image : BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext, debug: Boolean) {
        val contentBound = if (boxSizing == BoxSizing.BORDER_BOX) {
            boundRect
        } else {
            boundRect
        }
    }

    override fun measure(image : BufferedImage, graphics: Graphics2D, parentRect: BoundRect, context: TemplateContext): Area {
        return area
    }
}