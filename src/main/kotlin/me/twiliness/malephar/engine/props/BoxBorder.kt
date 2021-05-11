package me.twiliness.malephar.engine.props

import java.awt.Color

class BorderSide(val color: Color, val width: Float)

class BoxBorder(val top: BorderSide? = null, val left: BorderSide? = null, val right: BorderSide? = null, val bottom: BorderSide? = null) {
    companion object {
        fun all(borderSide: BorderSide) = BoxBorder(borderSide, borderSide, borderSide, borderSide)

        fun sym(horizontal: BorderSide? = null, vertical: BorderSide?): BoxBorder {
            assert(horizontal != null || vertical != null)

            return BoxBorder(vertical, horizontal, horizontal, vertical)
        }
    }
}