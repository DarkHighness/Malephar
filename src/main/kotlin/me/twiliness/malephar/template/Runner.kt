package me.twiliness.malephar.template

import com.facebook.yoga.*
import com.jhlabs.image.GaussianFilter
import com.jhlabs.image.InvertFilter
import me.twiliness.malephar.template.option.Padding
import me.twiliness.malephar.template.core.TemplateBuilder
import me.twiliness.malephar.template.core.TemplateContextBuilder
import java.awt.Font
import java.io.File

object Runner {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = YogaConfigFactory.create()

        val root = YogaNodeFactory.create();
        root.setWidth(500f);
        root.setHeight(300f);
        root.setJustifyContent(YogaJustify.CENTER);

        val node1 = YogaNodeFactory.create();
        node1.setWidth(100f);
        node1.setHeight(100f);

        val node2 = YogaNodeFactory.create();
        node2.setWidth(100f);
        node2.setHeight(100f);

        root.addChildAt(node1, 0);
        root.addChildAt(node2, 1);

        root.calculateLayout(500f, 300f);
        println("${root.layoutX} ${root.layoutY} ${root.layoutWidth} ${root.layoutHeight}")
        println("${node1.layoutX} ${node1.layoutY} ${node1.layoutWidth} ${node1.layoutHeight}")
        println("${node2.layoutX} ${node2.layoutY} ${node2.layoutWidth} ${node2.layoutHeight}")

        val robotoFont = Font.createFont(Font.TRUETYPE_FONT, File("assets/Roboto.ttf"))
        val poppinsFont = Font.createFont(Font.TRUETYPE_FONT, File("assets/Poppins.ttf"))
        val chineseFont = Font.createFont(Font.TRUETYPE_FONT, File("assets/chinese.ttf"))

        val template = TemplateBuilder()
            .layout {
                layer {
                    layerName = "root"
                    child = view {
                        child = filter {
                            op = GaussianFilter(2f)
                            child = image {
                                slotKey = "bg"
                            }
                        }
                    }
                }

                layer {
                    layerName = "name"
                    child = view {
                        padding = Padding.all(8)
                        child = center {
                            child = text {
                                slotKey = "name"
                                fontStyle = chineseFont.deriveFont(48.0f)
                            }
                        }
                    }
                }
            }
            .dimension(400, 400)
            .debug(false)
            .build()

        val context = TemplateContextBuilder()
            .imageSlot("bg", "assets/bg.png")
            .textSlot("name","DH是笨比")
            .build()

        template.render(context)
    }
}