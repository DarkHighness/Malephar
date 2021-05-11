package me.twiliness.malephar.engine.util

import java.awt.BasicStroke
import java.awt.Color
import java.awt.Font
import java.awt.Graphics2D
import kotlin.math.roundToInt

object Tools {
    private fun getComponentName(): String {
        return Throwable()
            .fillInStackTrace()
            .stackTrace
            .find { it.className.endsWith("Component") }
            ?.className
            ?.substringAfterLast(".") ?: ""
    }

    fun drawStringCenter(string: String, graphics: Graphics2D, fontStyle: Font, boundRect: BoundRect) {
        val fontBackup = graphics.font

        graphics.font = fontStyle

        val fontMetrics = graphics.getFontMetrics(fontStyle)
        val stringWidth = fontMetrics.stringWidth(string)

        graphics.drawString(
            string,
            boundRect.x + boundRect.width / 2 - stringWidth / 2,
            boundRect.y + boundRect.height / 2 +fontMetrics.ascent  - fontMetrics.leading)

        graphics.font = fontBackup
    }

    fun drawDebugRect(graphics: Graphics2D, boundRect: BoundRect, color: Color = Color.RED) {
        graphics.color = color

        val stroke = graphics.stroke as BasicStroke
        val debugStroke = BasicStroke(2f, BasicStroke.CAP_ROUND,BasicStroke.JOIN_BEVEL)

        graphics.stroke = debugStroke

        graphics.drawRect(
            boundRect.x,
            boundRect.y,
            boundRect.width - stroke.lineWidth.roundToInt(),
            boundRect.height - stroke.lineWidth.roundToInt())

        val caller = getComponentName()
        val debugFont = graphics.font.deriveFont(11f)

        drawStringCenter(caller, graphics, debugFont, boundRect)

        graphics.stroke = stroke
        graphics.color = Color.white
    }
}