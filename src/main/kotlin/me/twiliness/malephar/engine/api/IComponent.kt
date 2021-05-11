package me.twiliness.malephar.engine.api

import me.twiliness.malephar.engine.TemplateContext
import java.awt.Graphics2D
import java.awt.image.BufferedImage

interface IComponent {
    fun render(canvas: BufferedImage, graphics: Graphics2D, state: ComponentState, parentAnchor: Anchor, context: TemplateContext, debug: Boolean)

    fun collect(canvas: BufferedImage, graphics: Graphics2D, parentState: ComponentState, context: TemplateContext) : ComponentState
}