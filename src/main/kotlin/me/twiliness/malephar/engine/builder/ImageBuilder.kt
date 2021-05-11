package me.twiliness.malephar.engine.builder

import me.twiliness.malephar.engine.component.BoxComponent
import me.twiliness.malephar.engine.component.ImageComponent
import me.twiliness.malephar.engine.props.ImageFit

class ImageBuilder : SizedBoxBuilder() {
    lateinit var slotKey: String
    var imageFit: ImageFit = ImageFit.Cover

    override fun build(): ImageComponent {
        return ImageComponent(
            slotKey,
            imageFit,
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
}