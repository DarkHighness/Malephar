package me.twiliness.malephar.engine.props

data class BoxMargin(val top: Float = 0f,
                     val left: Float = 0f,
                     val right: Float = 0f,
                     val bottom: Float = 0f) {
    companion object {
        fun all(size: Float)
                = BoxMargin(size, size, size, size)

        fun sym(horizontal: Float = 0f, vertical: Float = 0f)
                = BoxMargin(vertical, horizontal, horizontal, vertical)

        fun only(top: Float = 0f, left: Float = 0f, right: Float = 0f, bottom: Float = 0f)
                = BoxMargin(top, left, right, bottom)
    }
}