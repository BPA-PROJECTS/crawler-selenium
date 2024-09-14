package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vDev

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping

@Tag(name = "DEV | Переменные окружения")
interface PropertiesDevEndpoint {

    @GetMapping("/properties")
    @Operation(summary = "Получение информации о переменных окружения")
    fun getProperties(): String
}