package me.twiliness.malephar.engine.builder

import me.twiliness.malephar.engine.component.SizedBoxComponent

class SizedBoxBuilder : BoxBuilder() {
    var width: Float? = null
    var height: Float? = null
    var widthPercent: Float? = null
    var heightPercent: Float? = null
    var minWidth: Float? = null
    var minHeight: Float? = null
    var maxWidth: Float? = null
    var maxHeight: Float? = null
    var minWidthPercent: Float? = null
    var minHeightPercent: Float? = null
    var maxWidthPercent: Float? = null
    var maxHeightPercent: Float? = null

    override fun build(): SizedBoxComponent {
        return SizedBoxComponent(
            child,
            decoration,
            margin,
            padding,
            boxSizing,
            width,
            height,
            widthPercent,
            heightPercent,
            minWidth,
            minHeight,
            maxWidth,
            maxHeight,
            minWidthPercent,
            minHeightPercent,
            maxWidthPercent,
            maxHeightPercent,
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
}