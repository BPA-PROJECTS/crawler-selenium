package ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state

import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerStatus

data class OpenChatWorkerState(
    val chatId: String,
    override val status: WorkerStatus = WorkerStatus.WORK
) : AbstractWorkerState
