package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1.worker

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1.CreateWorkerRequestV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.worker.GetWorkersResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1.WorkerAdminEndpoint
import ru.isinsmartsoft.tgcrawlerselenium.api.mapper.v1.WorkerMapperV1
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.TelegramAccountCredentialsBO
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.scenario.TelegramScenario
import ru.isinsmartsoft.tgcrawlerselenium.service.worker.WorkerManagementService
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContextStandard
import java.util.*

@RestController
class WorkerAdminEndpointImpl(
    private val telegramScenario: TelegramScenario,
    private val workerManagementService: WorkerManagementService,
    private val workerMapperV1: WorkerMapperV1
) : WorkerAdminEndpoint {

    private val log = KotlinLogging.logger {}

    private val storage = HashMap<UUID, Worker>()

    override fun login(request: CreateWorkerRequestV1): UUID {
         val ctx = AppContextStandard()
        val credentials = TelegramAccountCredentialsBO(
            phoneNumber = request.phoneNumber,
            secretCode = request.secretCode
        )
        return workerManagementService.createDraftWorker(ctx, credentials)
    }


    override fun sendLoginCode(workerId: UUID, loginCode: String) {
         val ctx = AppContextStandard()
        workerManagementService.getAllDraftWorkers(ctx).find { it.id == workerId }?.let { worker ->
            telegramScenario.authByCode(ctx, worker, loginCode)
        }
    }


    override fun actionOnWorker(workerId: UUID) {
         val ctx = AppContextStandard()
        val worker = storage[workerId]!!
        log.info { worker.id }
    }


    override fun getAllDraftWorkers(): GetWorkersResponseV1 {
         val ctx = AppContextStandard()
        val draftWorkers = workerManagementService.getAllDraftWorkers(ctx)
        return workerMapperV1.mapToGetWorkersResponseV1FromWorkers(draftWorkers)
    }

    override fun getAllRunWorkers(): GetWorkersResponseV1 {
         val ctx = AppContextStandard()
        val workersBox = workerManagementService.getAllRunWorkersBox(ctx)
        return workerMapperV1.mapToGetWorkersResponseV1(workersBox)
    }
}