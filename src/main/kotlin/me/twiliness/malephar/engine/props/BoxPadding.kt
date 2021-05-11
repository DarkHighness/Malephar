package me.twiliness.malephar.engine.props

data class BoxPadding(val top: Float, val left: Float, val right: Float, val bottom: Float) {
    companion object {
        fun all(size: Float)
                = BoxPadding(size, size, size, size)

        fun sym(horizontal: Float = 0f, vertical: Float = 0f)
                = BoxPadding(vertical, horizontal, horizontal, vertical)

        fun only(top: Float = 0f, left: Float = 0f, right: Float = 0f, bottom: Float = 0f)
                = BoxPadding(top, left, right, bottom)
    }
}
