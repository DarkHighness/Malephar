package me.twiliness.malephar.template.element

import me.twiliness.malephar.template.core.TemplateElementBuilder
import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import me.twiliness.malephar.engine.util.Tools
import java.awt.Font
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class TextElement(val slotKey: String, val fontStyle: Font) : TemplateElement {
    override fun render(image : BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext, debug: Boolean) {
        graphics.font = fontStyle

        val string = context.getText(slotKey)
        val fontMetrics = graphics.getFontMetrics(fontStyle)

        graphics.drawString(string, boundRect.x, boundRect.y + fontMetrics.ascent  - fontMetrics.leading)

        graphics.font = context.defaultFontStyle

        if (debug)
            Tools.drawDebugRect(graphics, boundRect)
    }

    override fun measure(image : BufferedImage, graphics: Graphics2D, parentRect: BoundRect, context: TemplateContext): Area {
        val string = context.getText(slotKey)

        val fontMetrics = graphics.getFontMetrics(fontStyle)
        val width = fontMetrics.stringWidth(string)
        val height = fontMetrics.height

        return Area(width, height)
    }

    class Builder : TemplateElementBuilder<TextElement> {
        lateinit var slotKey: String
        lateinit var fontStyle: Font

        override fun build(): TextElement {
            return TextElement(slotKey, fontStyle)
        }
    }
}