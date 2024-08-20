package ru.isinsmartsoft.tgcrawlerselenium.tools.json

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class MapperTool {
    companion object {
        val mapper: ObjectMapper = ObjectMapper().also {
            it.registerKotlinModule()
            it.registerModule(JavaTimeModule())
        }

    }
}