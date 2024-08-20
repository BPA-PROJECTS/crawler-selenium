package ru.isinsmartsoft.tgcrawlerselenium.dao.dto.worker

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state.AbstractWorkerState
import java.util.*

data class WorkerIdWithStateDTO(
    val workerId: UUID,
    val state: AbstractWorkerState
)
