package me.twiliness.malephar.engine.component

import me.twiliness.malephar.engine.TemplateContext
import me.twiliness.malephar.engine.api.*
import me.twiliness.malephar.engine.props.*
import me.twiliness.malephar.engine.util.BoundRect
import me.twiliness.malephar.engine.util.Tools
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage

open class BoxComponent(
    child: IComponent?,
    val decoration: BoxDecoration?,
    val margin: BoxMargin?,
    val padding: BoxPadding?,
    val boxSizing: BoxSizing,
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
) : FlexComponent(
    if (child == null)
        emptyList()
    else
        listOf(child),
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
    private fun drawBorderSide(graphics: Graphics2D, rect: BoundRect, edge: FlexEdge, borderSide: BorderSide?) {
        if (borderSide == null) return

        graphics.color = borderSide.color
        graphics.stroke = BasicStroke(borderSide.width)

        when(edge) {
            FlexEdge.LEFT -> graphics.drawLine(rect.x, rect.y, rect.x, rect.y + rect.height)
            FlexEdge.RIGHT -> graphics.drawLine(rect.x + rect.width, rect.y, rect.x + rect.width, rect.y + rect.height)
            FlexEdge.TOP -> graphics.drawLine(rect.x, rect.y, rect.x + rect.width, rect.y)
            FlexEdge.BOTTOM -> graphics.drawLine(rect.x, rect.y + rect.height, rect.x + rect.width, rect.y + rect.height)
            else -> {}
        }
    }

    override fun render(
        canvas: BufferedImage,
        graphics: Graphics2D,
        state: ComponentState,
        parentAnchor: Anchor,
        context: TemplateContext,
        debug: Boolean
    ) {
        if (decoration != null) {
            val color = graphics.color
            val rect = state.toBoundRect(parentAnchor)

            val backgroundColor = decoration.backgroundColor
            if (backgroundColor != null) {
                graphics.color = decoration.backgroundColor
                graphics.fillRect(
                    rect.x,
                    rect.y,
                    rect.width,
                    rect.height)
            }

            val border = decoration.border
            if (border != null) {
                drawBorderSide(graphics, rect, FlexEdge.LEFT, border.left)
                drawBorderSide(graphics, rect, FlexEdge.RIGHT, border.right)
                drawBorderSide(graphics, rect, FlexEdge.TOP, border.top)
                drawBorderSide(graphics, rect, FlexEdge.BOTTOM, border.bottom)
            }

            graphics.color = color
        }

        if (children.isNotEmpty()) {
            val childState = state.singleChildState()

            children.first().render(canvas, graphics, childState, parentAnchor.offset(state), context, debug)
        }

        if (debug) {
            Tools.drawDebugRect(graphics, state.toBoundRect(parentAnchor), componentName(), Color.red)

            context.logDebugInfo(this, state)
        }
    }

    override fun collect(
        canvas: BufferedImage,
        graphics: Graphics2D,
        parentState: ComponentState,
        context: TemplateContext
    ): ComponentState {
        val componentState = super.collect(canvas, graphics, parentState, context)

        if (margin != null) {
            componentState.setMargin(FlexEdge.LEFT, margin.left)
            componentState.setMargin(FlexEdge.RIGHT, margin.right)
            componentState.setMargin(FlexEdge.TOP, margin.top)
            componentState.setMargin(FlexEdge.BOTTOM, margin.bottom)
        }

        if (padding != null) {
            if (boxSizing == BoxSizing.BorderBox) {
                componentState.setPadding(FlexEdge.LEFT, padding.left)
                componentState.setPadding(FlexEdge.RIGHT, padding.right)
                componentState.setPadding(FlexEdge.TOP, padding.top)
                componentState.setPadding(FlexEdge.BOTTOM, padding.bottom)
            } else {
                componentState.setBorder(FlexEdge.LEFT, padding.left)
                componentState.setBorder(FlexEdge.RIGHT, padding.right)
                componentState.setBorder(FlexEdge.TOP, padding.top)
                componentState.setBorder(FlexEdge.BOTTOM, padding.bottom)
            }
        }

//        if (children.isNotEmpty()) {
//            val childState = children.first().collect(canvas, graphics, parentState, context)
//
//            componentState.pushChildState(childState)
//        }

        return componentState
    }

    override fun componentName(): String {
        return "Box"
    }
}