package ru.isinsmartsoft.tgcrawlerselenium.service.telegram

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerPageState
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

interface WorkerPageStateService {

    fun determineWorkerPageState(ctx: AppContext, worker: Worker): WorkerPageState
}