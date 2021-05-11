package me.twiliness.malephar.template.flex

import me.twiliness.malephar.template.core.TemplateContext
import me.twiliness.malephar.template.core.TemplateElement
import me.twiliness.malephar.template.flex.option.*
import me.twiliness.malephar.engine.util.Area
import me.twiliness.malephar.engine.util.BoundRect
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class FlexElement(val flexDirection: Axis,
                  val mainAxisAlignment: MainAxisAlignment,
                  val crossAxisAlignment: CrossAxisAlignment,
                  val mainAxisGap: Int,
                  val crossAxisGap: Int,
                  val flexFit: FlexFit,
                  val flexWrap: FlexWrap,
                  val children: List<FlexElement>,
                  val flexGrow: Int,
                  val flexShrink: Int,
                  val flexBias: FlexBias) : TemplateElement {

    interface FlexLayout {
        fun layout(container: FlexElement, boundRect: BoundRect, childrenArea: List<Area>)
    }

    class HorizontalImpl : FlexLayout{
        override fun layout(container: FlexElement, boundRect: BoundRect, childrenArea: List<Area>) {
            val sumChildWidth = childrenArea.sumBy { it.width }
            val sumWidth = sumChildWidth + container.mainAxisGap * (container.children.size - 1)

            if (sumWidth > boundRect.width && container.flexWrap == FlexWrap.noWrap) {
                // overflow, try shrink
                val childrenShrink = container.children.map { it.flexShrink }
                val sumChildrenShrink = childrenShrink.sum()

            }
        }
    }

    class VerticalImpl : FlexLayout {
        override fun layout(container: FlexElement, boundRect: BoundRect, childrenArea: List<Area>) {

        }
    }

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

    fun isFlexItem() : Boolean {
        return children.size == 1
    }

    fun computeBias() : Int {
        if (isFlexItem()) {
            val child = children.single()
        }

        return 0
    }
}