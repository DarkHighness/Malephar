package me.twiliness.malephar.engine.props

import com.facebook.yoga.YogaUnit
import com.facebook.yoga.YogaValue

typealias FlexValue = YogaValue
typealias FlexValueUnit = YogaUnit

fun createFlexValue(value: String) : FlexValue = FlexValue.parse(value)