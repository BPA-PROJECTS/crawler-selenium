package ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.worker

import io.swagger.v3.oas.annotations.media.Schema
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerPageState
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerStatus
import java.util.*

@Schema(description = "Информация о worker")
data class WorkerResponseV1(
    val workerId: UUID,
    val status: WorkerStatus,
    val pageState: WorkerPageState,
    val taskQueue: WorkerTaskQueueResponseV1
)
