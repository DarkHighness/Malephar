package me.twiliness.malephar.template.model

import java.awt.image.BufferedImage

sealed class Slot {
    class TextSlot(val text: String) : Slot()
    class ImageSlot(val image: BufferedImage) : Slot()
}