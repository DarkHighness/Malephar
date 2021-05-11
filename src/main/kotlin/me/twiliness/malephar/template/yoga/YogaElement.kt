package me.twiliness.malephar.template.yoga

import com.facebook.yoga.YogaNodeJNIFinalizer
import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import me.twiliness.malephar.engine.util.Tools
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import kotlin.math.roundToInt

class YogaElement(
    private val parent : YogaElement?,
    private val children: List<YogaElement>
) :
    YogaNodeJNIFinalizer(), TemplateElement {

    init {
        children.forEachIndexed { i, e -> addChildAt(e, i) }
    }

    override fun render(
        image: BufferedImage,
        graphics: Graphics2D,
        boundRect: BoundRect,
        context: TemplateContext,
        debug: Boolean
    ) {
        if (this.isDirty)
            this.calculateLayout(
                boundRect.width.toFloat(),
                boundRect.height.toFloat()
            )

        if (debug)
            Tools.drawDebugRect(graphics, BoundRect(
                this.layoutX.roundToInt(),
                this.layoutY.roundToInt(),
                this.layoutWidth.roundToInt(),
                this.layoutHeight.roundToInt()
            )
            )

        for (child in children)
            child.render(image, graphics, BoundRect(
                child.layoutX.roundToInt(),
                child.layoutY.roundToInt(),
                child.layoutWidth.roundToInt(),
                child.layoutHeight.roundToInt()
            ), context, debug)
    }

    override fun measure(
        image: BufferedImage,
        graphics: Graphics2D,
        parentRect: BoundRect,
        context: TemplateContext
    ): Area {
        if (this.isDirty)
            this.calculateLayout(
                parentRect.width.toFloat(),
                parentRect.height.toFloat()
            )

        return Area(
            this.layoutWidth.roundToInt(),
            this.layoutHeight.roundToInt()
        )
    }
}