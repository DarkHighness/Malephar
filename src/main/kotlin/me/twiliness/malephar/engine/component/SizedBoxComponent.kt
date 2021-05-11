package me.twiliness.malephar.engine.component

import me.twiliness.malephar.engine.TemplateContext
import me.twiliness.malephar.engine.api.ComponentState
import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.props.*
import java.awt.Graphics2D
import java.awt.image.BufferedImage

open class SizedBoxComponent(
    child: IComponent?,
    decoration: BoxDecoration?,
    margin: BoxMargin?,
    padding: BoxPadding?,
    boxSizing: BoxSizing,
    val width: Float?,
    val height: Float?,
    val widthPercent: Float?,
    val heightPercent: Float?,
    val minWidth: Float?,
    val minHeight: Float?,
    val maxWidth: Float?,
    val maxHeight: Float?,
    val minWidthPercent: Float?,
    val minHeightPercent: Float?,
    val maxWidthPercent: Float?,
    val maxHeightPercent: Float?,
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
) : BoxComponent(
    child,
    decoration,
    margin,
    padding,
    boxSizing,
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

    override fun collect(
        canvas: BufferedImage,
        graphics: Graphics2D,
        parentState: ComponentState,
        context: TemplateContext
    ): ComponentState {
        return super.collect(canvas, graphics, parentState, context).let {
            if (width != null) it.setWidth(width)
            if (height != null) it.setHeight(height)
            if (widthPercent != null) it.setWidthPercent(widthPercent)
            if (heightPercent != null) it.setHeightPercent(heightPercent)
            if (minWidth != null) it.setMinWidth(minWidth)
            if (minHeight != null) it.setMinHeight(minHeight)
            if (maxWidth != null) it.setMaxWidth(maxWidth)
            if (maxHeight != null) it.setMaxHeight(maxHeight)
            if (minWidthPercent != null) it.setWidthPercent(minWidthPercent)
            if (minHeightPercent != null) it.setHeightPercent(minHeightPercent)
            if (maxWidthPercent != null) it.setMaxWidthPercent(maxWidthPercent)
            if (maxHeightPercent != null) it.setMaxHeightPercent(maxHeightPercent)

            if (width == null && widthPercent == null)
                it.setWidthAuto()

            if (height == null && heightPercent == null)
                it.setHeightAuto()

            return@let it
        }
    }

    override fun componentName(): String {
        return "SizedBox"
    }
}