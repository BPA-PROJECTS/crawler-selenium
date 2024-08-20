package ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state

import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerStatus

interface AbstractWorkerState {
    val status: WorkerStatus
}

class FreeWorkerState(
    override val status: WorkerStatus = WorkerStatus.FREE
) : AbstractWorkerState