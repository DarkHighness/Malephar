package me.twiliness.malephar.engine.builder

import me.twiliness.malephar.engine.api.IBuilder
import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.component.FlexComponent
import me.twiliness.malephar.engine.props.*

open class FlexBuilder : IBuilder<FlexComponent> {
    val children: MutableList<IComponent> = arrayListOf()
    var display: Display = Display.FLEX
    var position: Position = Position.RELATIVE
    var overflow: Overflow = Overflow.VISIBLE
    var flexDirection: FlexDirection = FlexDirection.ROW
    var flexGrow: Float = 0f
    var flexShrink: Float = 1f
    var flexBasis: FlexValue = FlexValue.parse("auto")
    var flexWrap: FlexWrap = FlexWrap.NO_WRAP
    var justifyContent: FlexJustify = FlexJustify.FLEX_START
    var alignItems: FlexAlign = FlexAlign.STRETCH
    var alignContents: FlexAlign = FlexAlign.FLEX_START
    var alignSelf: FlexAlign = FlexAlign.AUTO
    var top: Float = 0f
    var left: Float = 0f
    var right: Float = 0f
    var bottom: Float = 0f

    override fun build(): FlexComponent {
        return FlexComponent(
            children,
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

    open fun flex(init: FlexBuilder.() -> Unit): Unit =
        FlexBuilder().apply(init).build().let { children.add(it) }

    open fun box(init: BoxBuilder.() -> Unit): Unit =
        BoxBuilder().apply(init).build().let { children.add(it) }

    open fun sizedBox(init: SizedBoxBuilder.() -> Unit): Unit =
        SizedBoxBuilder().apply(init).build().let { children.add(it) }

    open fun center(init: CenterBuilder.() -> Unit): Unit =
        CenterBuilder().apply(init).build().let { children.add(it) }

    open fun image(init: ImageBuilder.() -> Unit): Unit =
        ImageBuilder().apply(init).build().let { children.add(it) }
}