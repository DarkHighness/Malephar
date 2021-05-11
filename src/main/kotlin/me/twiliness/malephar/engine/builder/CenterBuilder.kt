package me.twiliness.malephar.engine.builder

import me.twiliness.malephar.engine.component.BoxComponent
import me.twiliness.malephar.engine.component.CenterComponent
import me.twiliness.malephar.engine.props.FlexJustify

class CenterBuilder : BoxBuilder() {
    override fun build(): CenterComponent {
        return CenterComponent(
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
            alignContents,
            alignSelf,
            top,
            left,
            right,
            bottom
        )
    }
}