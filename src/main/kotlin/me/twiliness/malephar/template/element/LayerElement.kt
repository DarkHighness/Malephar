package me.twiliness.malephar.template.element

import me.twiliness.malephar.template.core.TemplateElementBuilder
import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.BoundRect
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class LayerElement(val layerName: String,
                   child: TemplateElement
) : SingleChildElement(child) {
    override fun render(image : BufferedImage, graphics: Graphics2D, boundRect: BoundRect, context: TemplateContext, debug: Boolean) {
        child.render(image, graphics, boundRect, context
            .copyInto()
            .env("layer", layerName)
            .build(), debug)
    }

    class Builder : TemplateElementBuilder<LayerElement> {
        lateinit var layerName: String
        lateinit var child: TemplateElement

        override fun build(): LayerElement {
            return LayerElement(
                layerName,
                child
            )
        }
    }
}