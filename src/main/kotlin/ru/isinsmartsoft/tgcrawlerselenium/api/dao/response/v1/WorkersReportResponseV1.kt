package ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1

import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.worker.WorkerResponseV1

data class WorkersReportResponseV1(
    val data: List<WorkerResponseV1>
)
