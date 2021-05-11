package me.twiliness.malephar.engine.api

import com.facebook.yoga.YogaConfig
import com.facebook.yoga.YogaConfigFactory
import com.facebook.yoga.YogaNode
import com.facebook.yoga.YogaNodeFactory
import me.twiliness.malephar.engine.props.FlexEdge
import me.twiliness.malephar.engine.util.BoundRect
import java.awt.Point

typealias ComponentState = YogaNode
typealias Anchor = Point
typealias LayerState = YogaNode
typealias LayerConfig = YogaConfig

fun createComponentState(): ComponentState = YogaNodeFactory.create()
fun createComponentState(config: LayerConfig): ComponentState = YogaNodeFactory.create(config)

fun createLayerState(): LayerState = YogaNodeFactory.create()
fun createLayerState(config: LayerConfig): LayerState = YogaNodeFactory.create(config)

fun createLayerConfig(): LayerConfig = YogaConfigFactory.create()

fun ComponentState.pushChildState(child: ComponentState) = this.addChildAt(child, this.childCount)
fun ComponentState.singleChildState() = when (this.childCount) {
    1 -> this.getChildAt(0)
    else -> error("component has multiple children.")
}

fun ComponentState.toBoundRect(anchor: Point): BoundRect = toBoundRect(anchor.x, anchor.y)
fun ComponentState.outputToString() : String {
    val layoutString = "Layout: [${this.layoutX},${this.layoutY}]"
    val layoutSizeString = "Layout: Size: [${this.layoutWidth},${this.layoutHeight}]"
    val sizeString = "Size: [${this.width},${this.height}]"
    val marginString = "Margin: [${this.getMargin(FlexEdge.TOP)},${this.getMargin(FlexEdge.LEFT)},${this.getMargin(FlexEdge.RIGHT)},${this.getMargin(FlexEdge.BOTTOM)}]"
    val paddingString = "Padding: [${this.getPadding(FlexEdge.TOP)},${this.getPadding(FlexEdge.LEFT)},${this.getPadding(FlexEdge.RIGHT)},${this.getPadding(FlexEdge.BOTTOM)}]"
    val borderString = "Border: [${this.getBorder(FlexEdge.TOP)},${this.getBorder(FlexEdge.LEFT)},${this.getBorder(FlexEdge.RIGHT)},${this.getBorder(FlexEdge.BOTTOM)}]"

    return listOf(layoutString, layoutSizeString, sizeString, marginString, paddingString, borderString).joinToString("\n")
}

fun ComponentState.toBoundRect(dx: Int, dy: Int) = BoundRect(
    this.layoutX.toInt() + dx,
    this.layoutY.toInt() + dy,
    this.layoutWidth.toInt(),
    this.layoutHeight.toInt()
)

fun ComponentState.toBoundRect(): BoundRect = BoundRect(
    this.layoutX.toInt(),
    this.layoutY.toInt(),
    this.layoutWidth.toInt(),
    this.layoutHeight.toInt()
)

fun Anchor.offset(dx: Int, dy: Int): Anchor = Anchor(
    x + dx,
    y + dy
)

fun Anchor.offset(state: ComponentState): Anchor = offset(state.layoutX.toInt(), state.layoutY.toInt())

fun LayerState.pushRootState(root: ComponentState) = this.addChildAt(root, 0)
