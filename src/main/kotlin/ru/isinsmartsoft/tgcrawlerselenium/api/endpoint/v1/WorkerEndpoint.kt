package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import ru.isinsmartsoft.tgcrawlerselenium.constants.EndpointConstants
import java.util.*

@Tag(name = "V1 | Управление Worker")
@RequestMapping("${EndpointConstants.V1}/workers")
interface WorkerEndpoint {

    @GetMapping("/{workerId}/read-chat/{chatId}")
    @Operation(summary = "Отправка login code")
    fun readMessage(@PathVariable workerId: UUID, @PathVariable chatId: String)
}