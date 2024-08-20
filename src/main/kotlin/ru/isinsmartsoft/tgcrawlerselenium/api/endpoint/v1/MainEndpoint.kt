package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.v1

import io.github.oshai.kotlinlogging.KotlinLogging
import io.swagger.v3.oas.annotations.Operation
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.KafkaConstants
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.OPTIONS
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.seleniumProdGridUrl
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContextStandard
import java.net.URL
import java.util.UUID

@RestController
class MainEndpoint(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    private val log = KotlinLogging.logger {}

    @PostMapping("/main/worker")
    @Operation(summary = "Попытка создать worker")
    fun createWorker(@RequestBody request: SpecialRequest) {
        create(request)
    }

    fun create(request: SpecialRequest) {
        log.info { "Попытка создать worker path = ${request.path}" }
        val ctx = AppContextStandard()
        val cap = DesiredCapabilities()

        cap.setCapability(ChromeOptions.CAPABILITY, OPTIONS)
        cap.browserName = "chrome"

        val driver = RemoteWebDriver(URL(request.path), cap);
        log.info { "Попытка создать worker => Успешно" }
    }

    data class SpecialRequest(
        val path: String
    )
}