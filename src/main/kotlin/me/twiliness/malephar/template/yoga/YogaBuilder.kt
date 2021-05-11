package me.twiliness.malephar.template.yoga

import com.facebook.yoga.YogaConfig
import com.facebook.yoga.YogaConfigFactory
import com.facebook.yoga.YogaNodeFactory
import com.facebook.yoga.YogaValue
import me.twiliness.malephar.template.core.TemplateElementBuilder
import me.twiliness.malephar.template.element.TextElement
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

//class YogaBuilder : TemplateElementBuilder<YogaElement> {
//    val childrenList: MutableList<YogaElement> = ArrayList()
//
//    var config: YogaConfig = YogaConfigFactory.create()
//
//    lateinit var children : YogaBuilder.() -> Unit
//
//    fun child(child: YogaElement) {
//        this.childrenList.add(child)
//    }
//
//    fun child(init: () -> YogaElement) {
//        child(init())
//    }
//
//    override fun build(): YogaElement {
//        children(this)
//        return YogaElement(config, childrenList)
//    }
//
//    companion object {
//        fun yoga(init: YogaBuilder.() -> Unit): YogaElement {
//            return YogaBuilder()
//                .apply(init)
//                .build()
//        }
//
//        @JvmStatic
//        fun main(args: Array<String>) {
//            val yoga = yoga {
//                children = {
//                    child(YogaElement(YogaConfigFactory.create(), emptyList()))
//                }
//            }
//        }
//    }
//}