package me.twiliness.malephar.template.flex.option

sealed class FlexBias {
    object auto : FlexBias()
    object fill : FlexBias()
    object maxContent : FlexBias()
    object minContent : FlexBias()
    object fitContent : FlexBias()
    object content : FlexBias()
    class width(val width: Int) : FlexBias()
    class percent(val percent: Int) : FlexBias()
}