package me.twiliness.malephar.engine.builder

import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.api.ILayer
import me.twiliness.malephar.engine.api.ILayerBuilder
import me.twiliness.malephar.engine.core.LayerImpl

class LayerBuilder : ILayerBuilder {
    var child: IComponent? = null

    fun flexRoot(init: FlexBuilder.() -> Unit): ILayer {
        assert(child == null)

        child = FlexBuilder().apply(init).build()

        return build()
    }

    fun boxRoot(init: BoxBuilder.() -> Unit): ILayer {
        assert(child == null)

        child = BoxBuilder().apply(init).build()

        return build()
    }

    fun centerRoot(init: CenterBuilder.() -> Unit): ILayer {
        assert(child == null)

        child = CenterBuilder().apply(init).build()

        return build()
    }

    override fun build(): ILayer {
        return LayerImpl(
            checkNotNull(child)
        )
    }
}