package me.twiliness.malephar.template.yoga

import com.facebook.yoga.YogaConfig
import com.facebook.yoga.YogaConfigFactory
import com.facebook.yoga.YogaNodeJNIFinalizer
import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import me.twiliness.malephar.engine.util.toArea
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class YogaLayer(
    config: YogaConfig = YogaConfigFactory.create(),
    private val children: List<YogaElement>
) : YogaNodeJNIFinalizer(config), TemplateElement {

    private fun computeLayout(area: Area) {
        if (this.isDirty)
            this.calculateLayout(
                area.width.toFloat(),
                area.height.toFloat()
            )
    }

    override fun render(
        image: BufferedImage,
        graphics: Graphics2D,
        boundRect: BoundRect,
        context: TemplateContext,
        debug: Boolean
    ) {
        for (child in children)
            child.render(
                image, graphics,
                BoundRect(
                    child.layoutX.toInt(),
                    child.layoutY.toInt(),
                    child.layoutWidth.toInt(),
                    child.layoutHeight.toInt()
                ), context, debug
            )
    }

    override fun measure(
        image: BufferedImage,
        graphics: Graphics2D,
        parentRect: BoundRect,
        context: TemplateContext
    ): Area {
        computeLayout(parentRect.toArea())

        return Area(
            layoutWidth.toInt(),
            layoutHeight.toInt()
        )
    }
}