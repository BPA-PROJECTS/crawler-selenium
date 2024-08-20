package ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1

data class CreateWorkerRequestV1(
    val phoneNumber: String,
    val secretCode: String
)
