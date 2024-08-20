package ru.isinsmartsoft.tgcrawlerselenium.service.telegram

import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.dto.telegram.TelegramMessageDTO
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

@Service
class TelegramCrawlerServiceImpl : TelegramCrawlerService {
    override fun readMessages(ctx: AppContext, chatId: Long): List<TelegramMessageDTO> {
        TODO("Not yet implemented")
    }
}