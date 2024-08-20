package ru.isinsmartsoft.tgcrawlerselenium.service.observed_chats

import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

interface ObservedChatService {

    fun getObservedChatIds(ctx: AppContext): Set<String>

    fun editObservedChatIds(ctx: AppContext, observedChatIds: Set<String>)
}