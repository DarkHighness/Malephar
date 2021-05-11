package me.twiliness.malephar.engine.component

import me.twiliness.malephar.engine.TemplateContext
import me.twiliness.malephar.engine.api.ComponentState
import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.props.*
import java.awt.Graphics2D
import java.awt.image.BufferedImage

class CenterComponent(
    child: IComponent?,
    decoration: BoxDecoration?,
    margin: BoxMargin?,
    padding: BoxPadding?,
    boxSizing: BoxSizing,
    display: Display,
    position: Position,
    overflow: Overflow,
    flexDirection: FlexDirection,
    flexGrow: Float,
    flexShrink: Float,
    flexBasis: FlexValue,
    flexWrap: FlexWrap,
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
    FlexJustify.CENTER,
    FlexAlign.CENTER,
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
            // CenterComponent会占据父组件的所有空间

            it.setWidthPercent(100f)
            it.setHeightPercent(100f)

            return@let it
        }
    }

    override fun componentName(): String {
        return "Center"
    }
}