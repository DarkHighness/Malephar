package me.twiliness.malephar.engine

import me.twiliness.malephar.engine.builder.LayerBuilder
import me.twiliness.malephar.engine.props.BoxDecoration
import me.twiliness.malephar.engine.props.BoxMargin
import me.twiliness.malephar.engine.props.FlexWrap
import me.twiliness.malephar.engine.props.Overflow
import java.awt.Color
import java.io.File
import javax.imageio.ImageIO

class TemplateEngine {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val layer = LayerBuilder().flexRoot {
                flexWrap = FlexWrap.WRAP

                sizedBox {
                    margin = BoxMargin.all(8f)
                    width = 300f
                    height = 100f
                    decoration = BoxDecoration(
                        backgroundColor = Color.red
                    )
                }

                sizedBox {
                    margin = BoxMargin.all(8f)
                    width = 300f
                    height = 100f
                    decoration = BoxDecoration(
                        backgroundColor = Color.green
                    )
                }

                sizedBox {
                    margin = BoxMargin.all(8f)
                    width = 300f
                    height = 100f
                    decoration = BoxDecoration(
                        backgroundColor = Color.blue
                    )
                }

                sizedBox {
                    margin = BoxMargin.all(8f)
                    width = 300f
                    height = 100f
                    decoration = BoxDecoration(
                        backgroundColor = Color.orange
                    )
                }
            }

            val context = TemplateContext().apply {
                image("bg", ImageIO.read(File("assets/bg.png")))
            }

            val image = layer.render(800, 400, context, useWebDefault = true, debug = true)

            context.printDebugInfo()

            ImageIO.write(image, "png", File("output.png"))
        }
    }
}