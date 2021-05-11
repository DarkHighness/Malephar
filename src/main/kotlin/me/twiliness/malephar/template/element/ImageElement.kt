package me.twiliness.malephar.template.element

import me.twiliness.malephar.template.core.TemplateElementBuilder
import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import me.twiliness.malephar.engine.util.toArea
import me.twiliness.malephar.template.option.ObjectFit
import me.twiliness.malephar.engine.util.Tools
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class ImageElement(val slotKey: String, val objectFit: ObjectFit) : TemplateElement {
    override fun render(image : BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext, debug: Boolean) {
        graphics.drawImage(
            context.getImage(slotKey),
            boundRect.x,
            boundRect.y,
            boundRect.width,
            boundRect.height,
            null)

        if (debug)
            Tools.drawDebugRect(graphics, boundRect)
    }

    override fun measure(image : BufferedImage, graphics: Graphics2D, parentRect: BoundRect, context: TemplateContext): Area {
        return parentRect.toArea()
    }

    class Builder : TemplateElementBuilder<ImageElement> {
        lateinit var slotKey: String
        var objectFit : ObjectFit = ObjectFit.COVER

        override fun build(): ImageElement {
            return ImageElement(slotKey, objectFit)
        }
    }
}