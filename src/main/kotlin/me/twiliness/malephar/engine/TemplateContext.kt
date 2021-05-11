package me.twiliness.malephar.engine

import me.twiliness.malephar.engine.api.ComponentState
import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.api.outputToString
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.awt.Font
import java.awt.image.BufferedImage
import java.util.*

class TemplateContext {
    private val imageSlots = hashMapOf<String, BufferedImage>()
    private val textSlots = hashMapOf<String, String>()
    private val fontSlots = hashMapOf<String, Font>()
    private var logger: Logger = LoggerFactory.getLogger("Template Engine")
    private val componentTreeDebugStack: Stack<String> = Stack()

    fun getImage(slotKey: String) = imageSlots.getOrDefault(slotKey, BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB))

    fun getText(slotKey: String) = textSlots.getOrDefault(slotKey, "<Undefined>")

    fun getFont(slotKey: String) = fontSlots.getOrDefault(slotKey, Font.DIALOG)

    fun image(slotKey: String, image: BufferedImage) = imageSlots.put(slotKey, image)

    fun text(slotKey: String, text: String) = textSlots.put(slotKey, text)

    fun font(slotKey: String, font: Font) = fontSlots.put(slotKey, font)

    fun log(text: String) = logger.info(text)

    fun logDebugInfo(component: IComponent, componentState: ComponentState) {
        componentTreeDebugStack.push(
            "${component.componentName()}: \n${componentState.outputToString()} \n"
        )
    }

    fun printDebugInfo() {
        componentTreeDebugStack.asReversed().forEach(this::log)
    }
}