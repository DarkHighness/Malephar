package me.twiliness.malephar.engine.util

import me.twiliness.malephar.engine.api.ComponentState
import java.awt.Dimension
import java.awt.Rectangle

typealias BoundRect = Rectangle
typealias Area = Dimension

fun mkBound(x : Int, y : Int, width: Int, height: Int) = BoundRect(x,y,width, height)
fun mkArea(width: Int, height: Int) = Area(width, height)

fun BoundRect.expand(top : Int = 0, left : Int = 0, right : Int = 0, bottom : Int = 0): BoundRect {
    val nx = x - left
    val ny = y - top

    val nw = width + right
    val nh = height + bottom

    return BoundRect(nx, ny, nw, nh)
}

fun BoundRect.shrink(top : Int = 0, left : Int = 0, right : Int = 0, bottom : Int = 0): BoundRect {
    val nx = x + left
    val ny = y + top

    val nw = width - right
    val nh = height - bottom

    return BoundRect(nx, ny, nw, nh)
}

fun BoundRect.copy(): BoundRect {
    return BoundRect(
        x, y, width, height
    )
}

fun BoundRect.toArea() : Area {
    return Area(
        width, height
    )
}

fun BoundRect.offset(x: Float, y: Float) : BoundRect =
    offset(x.toInt(), y.toInt())

fun BoundRect.offset(x: Int, y: Int) : BoundRect =
    BoundRect(
        this.x + x, this.y + y, width, height
    )

fun BoundRect.offset(state: ComponentState) : BoundRect =
    offset(state.layoutX, state.layoutY)