package ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1

import java.util.*

data class MessageReportResponseV1(
    val workerId: UUID,
    val chatId: String,
    val messages: List<MessageResponseV1>
)

data class MessageResponseV1(
    val personId: String,
    val text: String
)