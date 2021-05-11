package me.twiliness.malephar.template.core

import me.twiliness.malephar.template.element.*
import okhttp3.internal.toImmutableList

class TemplateBuilder {
    private val layers : MutableList<LayerElement> = ArrayList()
    private var width : Int = 0
    private var height : Int = 0
    private var debug: Boolean = false

    fun layer(init: LayerElement.Builder.() -> Unit) : TemplateBuilder = apply {
        val layer = LayerElement
            .Builder()
            .apply(init)
            .build()

        this.layers.add(layer)
    }

    fun view(init: ContainerElement.Builder.() -> Unit) : ContainerElement =
        ContainerElement
            .Builder()
            .apply(init)
            .build()

    fun text(init: TextElement.Builder.() -> Unit) : TextElement =
        TextElement
            .Builder()
            .apply(init)
            .build()

    fun image(init: ImageElement.Builder.() -> Unit) : ImageElement =
        ImageElement
            .Builder()
            .apply(init)
            .build()

    fun center(init: CenterElement.Builder.() -> Unit) : CenterElement =
        CenterElement
            .Builder()
            .apply(init)
            .build()

    fun filter(init: FilterElement.Builder.() -> Unit) : FilterElement =
        FilterElement
            .Builder()
            .apply(init)
            .build()

    fun dimension(width: Int, height: Int) = apply {
        this.width = width
        this.height = height
    }

    fun debug(isDebug: Boolean = false) = apply {
        this.debug = isDebug
    }

    fun build() : Template {
        return Template(
            layers.toImmutableList(),
            width,
            height,
            debug
        )
    }

    fun layout(init: TemplateBuilder.() -> Unit) : TemplateBuilder = apply(init)
}