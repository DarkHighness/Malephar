package me.twiliness.malephar.engine.builder

import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.api.ILayer
import me.twiliness.malephar.engine.api.ILayerBuilder
import me.twiliness.malephar.engine.core.LayerImpl

class LayerBuilder : ILayerBuilder {
    lateinit var child: IComponent

    fun flex(init: FlexBuilder.() -> Unit): ILayer {
        child = FlexBuilder().apply(init).build()

        return build()
    }

    fun box(init: BoxBuilder.() -> Unit): ILayer {
        child = BoxBuilder().apply(init).build()

        return build()
    }

    override fun build(): ILayer {
        return LayerImpl(
            child
        )
    }
}