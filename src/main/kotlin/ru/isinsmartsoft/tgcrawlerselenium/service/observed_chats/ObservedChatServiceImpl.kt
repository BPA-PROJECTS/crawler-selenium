package ru.isinsmartsoft.tgcrawlerselenium.service.observed_chats

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

@Service
class ObservedChatServiceImpl : ObservedChatService {
    private val log = KotlinLogging.logger {}

    @Volatile
    private var observedChatIds: Set<String> = emptySet()

    override fun getObservedChatIds(ctx: AppContext): Set<String> {
        return observedChatIds
    }

    override fun editObservedChatIds(ctx: AppContext, observedChatIds: Set<String>) {
        this.observedChatIds = observedChatIds
    }
}