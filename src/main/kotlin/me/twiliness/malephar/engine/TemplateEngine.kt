package me.twiliness.malephar.engine

import me.twiliness.malephar.engine.builder.LayerBuilder
import me.twiliness.malephar.engine.component.FlexComponent
import me.twiliness.malephar.engine.component.SizedBoxComponent
import me.twiliness.malephar.engine.core.LayerImpl
import me.twiliness.malephar.engine.props.*
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

class TemplateEngine {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val layer = LayerBuilder().flex {
                flexGrow = 1f
                sizedBox {
                    width = 200f
                    height = 200f
                    decoration = BoxDecoration(
                        backgroundColor = Color(255,0,0,120)
                    )

                    sizedBox {
                        height = 100f
                        flexGrow = 1f
//                        boxSizing = BoxSizing.ContentBox
//                        padding = BoxPadding.all(8f)
                        margin = BoxMargin.all(8f)
                        decoration = BoxDecoration(
                            backgroundColor = Color(0,0,255,120)
                        )
                        sizedBox {
                            margin = BoxMargin.only(left = 8f, top = 4f)
                            height = 80f
                            decoration = BoxDecoration(
                                backgroundColor = Color(80,120,80,120),
                            )
                        }
                    }
                }
            }

            val image = layer.render(400, 200, TemplateContext(), useWebDefault = true, debug = true)

            ImageIO.write(image, "png", File("output.png"))
        }
    }
}