package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1.CreateWorkerRequestV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.worker.GetWorkersResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.EndpointConstants
import java.util.*

@Tag(name = "Admin V1 | Авторизация для Worker")
@RestController
@RequestMapping("${EndpointConstants.ADMIN_V1}/workers")
interface WorkerAdminEndpoint {
    @PostMapping("/login")
    @Operation(summary = "Залогиниться в worker")
    fun login(@RequestBody request: CreateWorkerRequestV1): UUID

    @PostMapping("/{workerId}/input-login-code/{loginCode}")
    @Operation(summary = "Отправка login code")
    fun sendLoginCode(@PathVariable workerId: UUID, @PathVariable loginCode: String)

    @GetMapping("/{workerId}/action")
    @Operation(summary = "Отправка login code")
    fun actionOnWorker(workerId: UUID)

    @GetMapping("/all-draft")
    @Operation(summary = "Получить информацию о all-draft workers")
    fun getAllDraftWorkers(): GetWorkersResponseV1

    @GetMapping("/all-run")
    @Operation(summary = "Получить информацию о all-run workers")
    fun getAllRunWorkers(): GetWorkersResponseV1
}