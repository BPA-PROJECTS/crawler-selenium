package ru.isinsmartsoft.tgcrawlerselenium.service.worker

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.TelegramAccountCredentialsBO
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.dto.worker.WorkerIdWithStateDTO
import ru.isinsmartsoft.tgcrawlerselenium.instrument.worker.WorkerBox
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext
import java.util.*

interface WorkerManagementService {

    fun createDraftWorker(ctx: AppContext, credentials: TelegramAccountCredentialsBO): UUID

    fun convertDraftToWorker(ctx: AppContext, workerId: UUID): WorkerBox

    fun getAllDraftWorkers(ctx: AppContext): List<Worker>

    fun getAllRunWorkersBox(ctx: AppContext): List<WorkerBox>

    fun getWorkersState(ctx: AppContext): List<WorkerIdWithStateDTO>

    fun getWorkerBoxByIds(ctx: AppContext, workerIds: Set<UUID>): Map<UUID, WorkerBox>

    fun deleteDraftWorker(ctx: AppContext, workerId: UUID)
    fun deleteRunWorker(ctx: AppContext, workerId: UUID)

}