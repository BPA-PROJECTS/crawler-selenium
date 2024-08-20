package ru.isinsmartsoft.tgcrawlerselenium.service.telegram

import ru.isinsmartsoft.tgcrawlerselenium.dao.dto.telegram.TelegramMessageDTO
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

interface TelegramCrawlerService {

    fun readMessages(ctx: AppContext, chatId: Long): List<TelegramMessageDTO>
}