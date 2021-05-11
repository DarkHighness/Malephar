package me.twiliness.malephar.template.flex

import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class FlexItemElement(val flexGrow: Int) : TemplateElement {
    override fun render(
        image: BufferedImage,
        graphics: Graphics2D,
        boundRect: BoundRect,
        context: TemplateContext,
        debug: Boolean
    ) {
        TODO("Not yet implemented")
    }

    override fun measure(
        image: BufferedImage,
        graphics: Graphics2D,
        parentRect: BoundRect,
        context: TemplateContext
    ): Area {
        TODO("Not yet implemented")
    }
}