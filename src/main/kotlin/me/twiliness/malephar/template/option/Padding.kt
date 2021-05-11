package me.twiliness.malephar.template.option

data class Padding(val top: Int, val left: Int, val right: Int, val bottom : Int) {
    companion object {
        fun all(size: Int)
            = Padding(size, size, size, size)

        fun sym(horizontal: Int = 0, vertical: Int = 0)
            = Padding(vertical, horizontal, horizontal, vertical)

        fun sides(top: Int = 0, left: Int = 0, right: Int = 0, bottom: Int = 0)
            = Padding(top, left, right, bottom)
    }
}