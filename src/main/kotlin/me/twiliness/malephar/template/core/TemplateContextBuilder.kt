package me.twiliness.malephar.template.core

import me.twiliness.malephar.template.option.FontStyle
import me.twiliness.malephar.template.model.Slot
import okhttp3.internal.toImmutableMap
import java.awt.Font
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class TemplateContextBuilder {
    private var font: Font = Font(Font.DIALOG, Font.PLAIN, 12)

    private val slotTextMap: MutableMap<String, Slot.TextSlot> = hashMapOf()
    private val slotImageMap: MutableMap<String, Slot.ImageSlot> = hashMapOf()
    private val envMap: MutableMap<String, Any> = hashMapOf()

    constructor()

    constructor(context: TemplateContext) {
        this.slotTextMap.putAll(context.slotTextMap)
        this.slotImageMap.putAll(context.slotImageMap)
        this.envMap.putAll(context.envMap)
    }

    fun defaultFontStyle(fontStyle: FontStyle) : TemplateContextBuilder = apply {
        this.font = Font(fontStyle.fontFamily, fontStyle.fontWeight, fontStyle.fontSize)
    }

    fun textSlot(key : String, text: String) : TemplateContextBuilder = apply {
        this.slotTextMap[key] = Slot.TextSlot(text)
    }

    fun imageSlot(key: String, image: BufferedImage) : TemplateContextBuilder = apply {
        this.slotImageMap[key] = Slot.ImageSlot(image)
    }

    fun env(key: String, value: Any) : TemplateContextBuilder = apply {
        this.envMap[key] = value
    }

    fun imageSlot(slotKey: String, imageUrl: String) = apply {
        val imageFile = File(imageUrl)

        if (imageFile.exists().not())
            error("image slot with key ${slotKey}, url: $imageUrl not exists")

        val image = ImageIO.read(imageFile)

        this.imageSlot(slotKey, image)
    }

    fun build() = TemplateContext(
        font,
        slotTextMap.toImmutableMap(),
        slotImageMap.toImmutableMap(),
        envMap.toImmutableMap())
}