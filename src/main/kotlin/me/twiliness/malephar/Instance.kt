package me.twiliness.malephar

import kotlinx.coroutines.runBlocking
import me.twiliness.malephar.conf.Config
import net.mamoe.mirai.BotFactory
import net.mamoe.mirai.alsoLogin
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.whileSelectMessages
import net.mamoe.mirai.message.data.MessageKey
import net.mamoe.mirai.message.data.PlainText

object Instance {
    @JvmStatic
    fun main(args: Array<String>): Unit = runBlocking {
        val conf = Config.loadConfig()

        val bot = BotFactory.newBot(conf.user.qq, conf.user.password) {
            fileBasedDeviceInfo()
        }.alsoLogin()
    }
}