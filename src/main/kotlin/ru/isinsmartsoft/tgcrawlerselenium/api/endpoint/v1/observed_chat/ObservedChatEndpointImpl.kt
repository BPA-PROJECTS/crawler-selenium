package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.v1.observed_chat

import org.springframework.web.bind.annotation.*
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1.observed_chat.ObservedChatsRequestV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.observed_chat.ObservedChatResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.observed_chat.ObservedChatsReportResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.v1.ObservedChatEndpoint
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.EndpointConstants
import ru.isinsmartsoft.tgcrawlerselenium.service.observed_chats.ObservedChatService
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContextStandard
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

@RestController
@RequestMapping("${EndpointConstants.V1}/observed_chats")
class ObservedChatEndpointImpl(
    private val observedChatService: ObservedChatService
) : ObservedChatEndpoint {

    @GetMapping
    override fun getObservedChats(): ObservedChatsReportResponseV1 {
         val ctx = AppContextStandard()
        return getObservedChats(ctx)
    }

    @PostMapping
    override fun editObservedChats(@RequestBody request: ObservedChatsRequestV1): ObservedChatsReportResponseV1 {
         val ctx = AppContextStandard()
        observedChatService.editObservedChatIds(ctx, request.chatIds)
        return getObservedChats(ctx)
    }

    private fun getObservedChats(ctx: AppContext): ObservedChatsReportResponseV1 {
        return ObservedChatsReportResponseV1(
            chats = observedChatService.getObservedChatIds(ctx).map { observedChatId ->
                ObservedChatResponseV1(observedChatId)
            }
        )
    }
}