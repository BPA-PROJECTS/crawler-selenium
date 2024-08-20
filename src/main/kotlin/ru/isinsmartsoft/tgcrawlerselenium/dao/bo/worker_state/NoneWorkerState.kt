package ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state

import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerStatus

data class NoneWorkerState(
    override val status: WorkerStatus = WorkerStatus.NONE
) : AbstractWorkerState