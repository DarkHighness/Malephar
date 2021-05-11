package me.twiliness.malephar.engine.builder

import me.twiliness.malephar.engine.api.IBuilder
import me.twiliness.malephar.engine.api.IComponent
import me.twiliness.malephar.engine.component.FlexComponent
import me.twiliness.malephar.engine.props.*

open class FlexBuilder : IBuilder<FlexComponent> {
    val children: MutableList<IComponent> = arrayListOf()
    var display: Display = Display.FLEX
    var position: Position = Position.RELATIVE
    var flexDirection: FlexDirection = FlexDirection.ROW
    var flexGrow: Float = 0f
    var flexShrink: Float = 1f
    var flexBasis: FlexValue = FlexValue.parse("auto")
    var flexWrap: FlexWrap = FlexWrap.NO_WRAP
    var justifyContent: FlexJustify = FlexJustify.FLEX_START
    var alignItems: FlexAlign = FlexAlign.FLEX_START
    var alignContents: FlexAlign = FlexAlign.FLEX_START
    var alignSelf: FlexAlign = FlexAlign.FLEX_START
    var top: Float = 0f
    var left: Float = 0f
    var right: Float = 0f
    var bottom: Float = 0f

    override fun build(): FlexComponent {
        return FlexComponent(
            children,
            display,
            position,
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

    open fun flex(init: FlexBuilder.() -> Unit) : Unit =
        FlexBuilder().apply(init).build().let { children.add(it) }

    open fun box(init: BoxBuilder.() -> Unit) : Unit =
        BoxBuilder().apply(init).build().let { children.add(it) }

    open fun sizedBox(init: SizedBoxBuilder.() -> Unit) : Unit =
        SizedBoxBuilder().apply(init).build().let { children.add(it) }
}