package me.twiliness.malephar.template.core

import me.twiliness.malephar.template.model.Slot
import java.awt.Font
import java.awt.image.BufferedImage

class TemplateContext(val defaultFontStyle: Font,
                      val slotTextMap: Map<String, Slot.TextSlot>,
                      val slotImageMap: Map<String, Slot.ImageSlot>,
                      val envMap: Map<String, Any>) {

    fun getImage(slotKey : String) : BufferedImage {
        val slot = slotImageMap[slotKey] ?: error("slot key not found")

        return slot.image
    }

    fun getText(slotKey: String) : String {
        val slot = slotTextMap[slotKey] ?: error("slot key not found")

        return slot.text
    }

    fun getEnv(slotKey: String) : Any {
        return envMap[slotKey] ?: error("slot key not found")
    }

    fun copyInto() : TemplateContextBuilder {
        return TemplateContextBuilder(this)
    }
}