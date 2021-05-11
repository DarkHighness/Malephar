package me.twiliness.malephar.engine.component

import me.twiliness.malephar.engine.TemplateContext
import me.twiliness.malephar.engine.api.*
import me.twiliness.malephar.engine.props.*
import me.twiliness.malephar.engine.util.Tools
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage

open class FlexComponent(
    protected val children: List<IComponent>,
    protected val display: Display,
    protected val position: Position,
//    protected val flexFit: FlexFit,
    protected val flexDirection: FlexDirection,
    protected val flexGrow: Float,
    protected val flexShrink: Float,
    protected val flexBasis: FlexValue,
    protected val flexWrap: FlexWrap,
    protected val justifyContent: FlexJustify,
    protected val alignItems: FlexAlign,
    protected val alignContent: FlexAlign,
    protected val alignSelf: FlexAlign,
    protected val top: Float,
    protected val left: Float,
    protected val right: Float,
    protected val bottom: Float
) : IComponent {
    override fun render(
        canvas: BufferedImage,
        graphics: Graphics2D,
        state: ComponentState,
        parentAnchor: Anchor,
        context: TemplateContext,
        debug: Boolean
    ) {
        if (debug) {
            Tools.drawDebugRect(graphics, state.toBoundRect(parentAnchor.x, parentAnchor.y), Color.green)

            println("${this::class.simpleName}: \n${state.outputToString()}")
        }

        for(idx in children.indices) {
            val childState = state.getChildAt(idx)

            children[idx].render(canvas, graphics, childState, parentAnchor.offset(state), context, debug)
        }
    }

    override fun collect(
        canvas: BufferedImage,
        graphics: Graphics2D,
        parentState: ComponentState,
        context: TemplateContext
    ): ComponentState {
        return createComponentState()
            .let {
                it.display = display
                it.positionType = position
                it.flexDirection = flexDirection

                it.flexGrow = flexGrow
                it.flexShrink = flexShrink
                it.wrap = flexWrap

                when(flexBasis.unit) {
                    FlexValueUnit.POINT -> it.setFlexBasis(flexBasis.value)
                    FlexValueUnit.PERCENT -> it.setFlexBasisPercent(flexBasis.value)
                    FlexValueUnit.AUTO -> it.setFlexBasisAuto()
                    else -> {}
                }

                it.justifyContent = justifyContent
                it.alignContent = alignContent
                it.alignItems = alignItems
                it.alignSelf = alignSelf

                it.setPosition(FlexEdge.TOP, top)
                it.setPosition(FlexEdge.LEFT, left)
                it.setPosition(FlexEdge.RIGHT, right)
                it.setPosition(FlexEdge.BOTTOM, bottom)

                for (idx in children.indices)
                    it.addChildAt(children[idx].collect(canvas, graphics, parentState, context), idx)

                return@let it
        }
    }
}