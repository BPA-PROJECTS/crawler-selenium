package ru.isinsmartsoft.tgcrawlerselenium.service.worker

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.TelegramAccountCredentialsBO
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state.FreeWorkerState
import ru.isinsmartsoft.tgcrawlerselenium.dao.dto.worker.WorkerIdWithStateDTO
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerBoxStatusEnum
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.scenario.TelegramScenario
import ru.isinsmartsoft.tgcrawlerselenium.exception.DraftWorkerNotFoundException
import ru.isinsmartsoft.tgcrawlerselenium.exception.RunWorkerNotFoundException
import ru.isinsmartsoft.tgcrawlerselenium.instrument.worker.WorkerBox
import ru.isinsmartsoft.tgcrawlerselenium.instrument.worker.WorkerTaskQueue
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext
import java.util.*
import java.util.concurrent.ConcurrentHashMap

@Service
class WorkerManagementServiceImpl(
    private val telegramScenario: TelegramScenario
) : WorkerManagementService {
    private val log = KotlinLogging.logger {}

    private val storageWorkersBox = ConcurrentHashMap<UUID, WorkerBox>()

    private val storageDraftWorkers = ConcurrentHashMap<UUID, Worker>()

    override fun createDraftWorker(ctx: AppContext, credentials: TelegramAccountCredentialsBO): UUID {
        val worker = Worker(credentials)
        telegramScenario.login(ctx, worker)
        storageDraftWorkers[worker.id] = worker
        return worker.id
    }

    override fun getAllDraftWorkers(ctx: AppContext): List<Worker> {
        return storageDraftWorkers.values.toList()
    }

    override fun getAllRunWorkersBox(ctx: AppContext): List<WorkerBox> {
        return storageWorkersBox.values.toList()
    }

    override fun getWorkersState(ctx: AppContext): List<WorkerIdWithStateDTO> {
        return storageWorkersBox.values.map { workerBox ->
            val worker = workerBox.worker
            WorkerIdWithStateDTO(
                workerId = worker.id,
                state = worker.state
            )
        }
    }

    override fun getWorkerBoxByIds(ctx: AppContext, workerIds: Set<UUID>): Map<UUID, WorkerBox> {
        return workerIds
            .mapNotNull { storageWorkersBox[it] }
            .associateBy { it.worker.id }
    }

    override fun convertDraftToWorker(ctx: AppContext, workerId: UUID): WorkerBox {
        val draftWorker = storageDraftWorkers[workerId] ?: throw DraftWorkerNotFoundException(workerId)
        draftWorker.state = FreeWorkerState()
        val workerBox = WorkerBox(draftWorker, WorkerBoxStatusEnum.NONE, WorkerTaskQueue())
        storageWorkersBox[workerBox.worker.id] = workerBox
        storageDraftWorkers.remove(workerBox.worker.id)
        return workerBox
    }

    override fun deleteDraftWorker(ctx: AppContext, workerId: UUID) {
        storageDraftWorkers.remove(workerId)
        log.info { "WorkerManagementService :: delete draft worker=$workerId => Success" }
    }

    override fun deleteRunWorker(ctx: AppContext, workerId: UUID) {
        val workerBox = storageWorkersBox[workerId] ?: throw RunWorkerNotFoundException(workerId)
        workerBox.queue.destroy()
        storageWorkersBox.remove(workerId)
        log.info { "WorkerManagementService :: delete run worker=$workerId => Success" }
    }
}