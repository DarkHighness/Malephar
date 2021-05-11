package me.twiliness.malephar.conf

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File

object Config {
    private val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

    suspend fun loadConfig() : ConfigModel {
        return mapper.readValue(File("conf.yml"))
    }
}