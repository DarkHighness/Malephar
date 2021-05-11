package me.twiliness.malephar.engine.builder

import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.component.BoxComponent
import me.twiliness.malephar.engine.props.BoxDecoration
import me.twiliness.malephar.engine.props.BoxMargin
import me.twiliness.malephar.engine.props.BoxPadding
import me.twiliness.malephar.engine.props.BoxSizing

open class BoxBuilder : FlexBuilder() {
    var child: IComponent? = null
    var decoration: BoxDecoration? = null
    var margin: BoxMargin? = null
    var padding: BoxPadding? = null
    var boxSizing: BoxSizing = BoxSizing.ContentBox

    override fun build(): BoxComponent {
        return BoxComponent(
            child,
            decoration,
            margin,
            padding,
            boxSizing,
            display,
            position,
            overflow,
            flexDirection,
            flexGrow,
            flexShrink,
            flexBasis,
            flexWrap,
            justifyContent,
            alignItems,
            alignContents,
            alignSelf,
            top,
            left,
            right,
            bottom
        )
    }

    override fun flex(init: FlexBuilder.() -> Unit): Unit =
        FlexBuilder().apply(init).build().let {
            assert(child == null)
            child = it
        }

    override fun box(init: BoxBuilder.() -> Unit): Unit =
        BoxBuilder().apply(init).build().let {
            assert(child == null)
            child = it
        }

    override fun sizedBox(init: SizedBoxBuilder.() -> Unit): Unit =
        SizedBoxBuilder().apply(init).build().let {
            assert(child == null)
            child = it
        }

    override fun center(init: CenterBuilder.() -> Unit): Unit =
        CenterBuilder().apply(init).build().let {
            assert(child == null)
            child = it
        }

    override fun image(init: ImageBuilder.() -> Unit): Unit =
        ImageBuilder().apply(init).build().let {
            assert(child == null)
            child = it
        }
}