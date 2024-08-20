package ru.isinsmartsoft.tgcrawlerselenium.listener

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerPageState
import ru.isinsmartsoft.tgcrawlerselenium.dao.event.AuditWorkerEvent
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.scenario.TelegramScenario
import ru.isinsmartsoft.tgcrawlerselenium.service.telegram.WorkerPageStateService
import ru.isinsmartsoft.tgcrawlerselenium.service.worker.WorkerManagementService
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContextStandard
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

@Service
class AuditWorkerEventListener(
    private val telegramScenario: TelegramScenario,
    private val workerManagementService: WorkerManagementService,
    private val workerPageStateService: WorkerPageStateService
) {
    private val log = KotlinLogging.logger {}

    @Async("threadPoolAuditWorkerExecutor")
    @EventListener(AuditWorkerEvent::class)
    fun onEvent(event: AuditWorkerEvent) {
         val ctx = AppContextStandard()
        processingDraftWorkers(ctx)
        processingRunWorkers(ctx)
    }

    private fun processingDraftWorkers(ctx: AppContext) {
        val draftWorkers = workerManagementService.getAllDraftWorkers(ctx)
        draftWorkers.forEach { worker ->
            val workerId = worker.id
            val workerPageState = workerPageStateService.determineWorkerPageState(ctx, worker)

            when (workerPageState) {
                WorkerPageState.INPUT_SECRET_CODE_PAGE -> {
                    telegramScenario.authBySecretCode(ctx, worker, worker.tgCredentials.secretCode)
                }

                WorkerPageState.WORKSPACE_PAGE -> {
                    workerManagementService.convertDraftToWorker(ctx, workerId)
                }

                WorkerPageState.UNDEFINED -> {
                    log.warn { "AuditWorkerEventListener :: Discovery worker with page state UNDEFINED | workerId: $workerId" }
                }

                WorkerPageState.NONE -> {
                    workerManagementService.deleteDraftWorker(ctx, workerId)
                }

                else -> {
                    log.info { "AuditWorkerEventListener :: $workerId , pageState: $workerPageState" }
                }
            }
        }
    }

    private fun processingRunWorkers(ctx: AppContext) {
        val allRunWorkersBox = workerManagementService.getAllRunWorkersBox(ctx)
        allRunWorkersBox.forEach { workerBox ->
            val worker = workerBox.worker
            val workerId = worker.id
            val workerPageState = workerPageStateService.determineWorkerPageState(ctx, worker)
            // log.info { "AuditWorkerEventListener :: $workerId , pageState: $workerPageState" }
            when (workerPageState) {
                WorkerPageState.NONE -> {
                    workerManagementService.deleteRunWorker(ctx, workerId)
                }

                else -> {
                    // log.info { "AuditWorkerEventListener: $workerPageState" }
                }
            }
        }
    }
}