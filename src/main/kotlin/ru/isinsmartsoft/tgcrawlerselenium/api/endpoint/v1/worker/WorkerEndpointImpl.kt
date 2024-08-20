package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.v1.worker

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.v1.WorkerEndpoint
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.MainPage
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.OpenChatPage
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.SignInPage
import ru.isinsmartsoft.tgcrawlerselenium.service.worker.WorkerManagementService
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContextStandard
import java.util.*

@RestController
class WorkerEndpointImpl(
    private val mainPage: MainPage,
    private val signInPage: SignInPage,
    private val openChatPage: OpenChatPage,
    private val workerManagementService: WorkerManagementService
) : WorkerEndpoint {

    private val log = KotlinLogging.logger {}

    override fun readMessage(workerId: UUID, chatId: String) {
         val ctx = AppContextStandard()
        val workerBox = workerManagementService.getWorkerBoxByIds(ctx, setOf(workerId))[workerId]!!
        openChatPage.openChat(ctx, workerBox.worker, chatId)
        val readMessages = openChatPage.readMessages(ctx, workerBox.worker, chatId)
        log.info { "readMessage:" }
        readMessages.forEach {
            log.info { it }
        }
    }
}