package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.dto.telegram.TelegramMessageDTO
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

interface OpenChatPage {

    fun openChat(ctx: AppContext, worker: Worker, chatId: String)

    fun readMessages(ctx: AppContext, worker: Worker, chatId: String): List<TelegramMessageDTO>

    fun clickButtonScrollDown(ctx: AppContext, worker: Worker)

    fun clickIfExistButtonScrollDown(ctx: AppContext, worker: Worker)
}