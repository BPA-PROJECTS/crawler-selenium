package ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.worker

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Информация о workers")
data class GetWorkersResponseV1(
    val data: List<WorkerResponseV1>
)
