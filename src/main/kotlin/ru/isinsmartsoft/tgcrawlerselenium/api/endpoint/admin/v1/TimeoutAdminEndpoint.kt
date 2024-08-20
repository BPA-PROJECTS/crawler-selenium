package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.admin.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1.timeout.EditTimeoutsRequestV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.timeout.GetTimeoutsResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.constants.EndpointConstants

@Tag(name = "Admin V1 | Управление таймаутами между действийями")
@RestController
@RequestMapping("${EndpointConstants.ADMIN_V1}/timeouts")
interface TimeoutAdminEndpoint {
    
    @GetMapping
    @Operation(summary = "Получение таймаутов")
    fun getTimeoutMap(): GetTimeoutsResponseV1


    @PostMapping
    @Operation(summary = "Изменение таймаутов")
    fun editTimeouts(request: EditTimeoutsRequestV1): GetTimeoutsResponseV1
}