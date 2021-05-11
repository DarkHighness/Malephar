package me.twiliness.malephar.engine.component

import me.twiliness.malephar.engine.TemplateContext
import me.twiliness.malephar.engine.api.Anchor
import me.twiliness.malephar.engine.api.ComponentState
import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.api.toBoundRect
import me.twiliness.malephar.engine.props.*
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.lang.Math.max
import java.lang.Math.min

class ImageComponent(
    val slotKey: String,
    val imageFit: ImageFit,
    child: IComponent?,
    decoration: BoxDecoration?,
    margin: BoxMargin?,
    padding: BoxPadding?,
    boxSizing: BoxSizing,
    width: Float?,
    height: Float?,
    widthPercent: Float?,
    heightPercent: Float?,
    minWidth: Float?,
    minHeight: Float?,
    maxWidth: Float?,
    maxHeight: Float?,
    minWidthPercent: Float?,
    minHeightPercent: Float?,
    maxWidthPercent: Float?,
    maxHeightPercent: Float?,
    display: Display,
    position: Position,
    overflow: Overflow,
    flexDirection: FlexDirection,
    flexGrow: Float,
    flexShrink: Float,
    flexBasis: FlexValue,
    flexWrap: FlexWrap,
    justifyContent: FlexJustify,
    alignItems: FlexAlign,
    alignContents: FlexAlign,
    alignSelf: FlexAlign,
    top: Float,
    left: Float,
    right: Float,
    bottom: Float
) : SizedBoxComponent(
    child,
    decoration,
    margin,
    padding,
    boxSizing,
    width,
    height,
    widthPercent,
    heightPercent,
    minWidth,
    minHeight,
    maxWidth,
    maxHeight,
    minWidthPercent,
    minHeightPercent,
    maxWidthPercent,
    maxHeightPercent,
    display,
    position,
    overflow,
    flexDirection,
    flexGrow,
    flexShrink,
    flexBasis,
    flexWrap,
    justifyContent,
    alignItems,
    alignContents,
    alignSelf,
    top,
    left,
    right,
    bottom
) {
    override fun render(
        canvas: BufferedImage,
        graphics: Graphics2D,
        state: ComponentState,
        parentAnchor: Anchor,
        context: TemplateContext,
        debug: Boolean
    ) {
        val rect = state.toBoundRect(parentAnchor)
        val image = context.getImage(slotKey)

        graphics.drawImage(
            image,
            rect.x,
            rect.y,
            min(rect.width, image.width),
            min(rect.height, image.height),
            null
        )

        super.render(canvas, graphics, state, parentAnchor, context, debug)
    }

    override fun collect(
        canvas: BufferedImage,
        graphics: Graphics2D,
        parentState: ComponentState,
        context: TemplateContext
    ): ComponentState {
        return super.collect(canvas, graphics, parentState, context).let {
            // 与CenterComponent相同，ImageComponent会占据父组件的所有空间

            it.setWidthPercent(100f)
            it.setHeightPercent(100f)

            return@let it
        }
    }

    override fun componentName(): String {
        return "Image"
    }
}