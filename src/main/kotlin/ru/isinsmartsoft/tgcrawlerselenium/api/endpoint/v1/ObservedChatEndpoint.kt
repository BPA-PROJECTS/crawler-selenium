package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1.observed_chat.ObservedChatsRequestV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.observed_chat.ObservedChatsReportResponseV1

@Tag(name = "V1 | Управление наблюдаемыми чатами")
interface ObservedChatEndpoint {

    @Operation(summary = "Получение наблюдаемых чатов")
    fun getObservedChats(): ObservedChatsReportResponseV1

    @Operation(summary = "Изменение списка наблюдаемых чатов")
    fun editObservedChats(request: ObservedChatsRequestV1): ObservedChatsReportResponseV1
}