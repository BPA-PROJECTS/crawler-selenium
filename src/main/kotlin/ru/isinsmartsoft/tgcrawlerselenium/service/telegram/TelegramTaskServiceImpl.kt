package ru.isinsmartsoft.tgcrawlerselenium.service.telegram

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.TimeoutReasonType
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.OpenChatPage
import ru.isinsmartsoft.tgcrawlerselenium.instrument.task.TelegramWorkerTask
import ru.isinsmartsoft.tgcrawlerselenium.integration.kafka.TelegramMessageProducer
import ru.isinsmartsoft.tgcrawlerselenium.service.telegram.timeout.TimeoutService
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext
import ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue.PriorityOfTaskEnum
import ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue.TaskWithPriority

@Service
class TelegramTaskServiceImpl(
    private val timeoutService: TimeoutService,
    private val openChatPage: OpenChatPage,
    private val telegramMessageProducer: TelegramMessageProducer
) : TelegramTaskService {

    private val log = KotlinLogging.logger {}

    override fun createTaskReadChat(ctx: AppContext, worker: Worker, chatId: String): TaskWithPriority {
        return ctx.run("TelegramTaskService :: Create task READ_CHAT chatId=$chatId") {
            object : TelegramWorkerTask(PriorityOfTaskEnum.THREE) {
                override fun run() {
                    openChatPage.openChat(ctx, worker, chatId)
                    timeoutService.timeout(TimeoutReasonType.AFTER_OPEN_CHAT_AND_BEFORE_READ_MESSAGES)
                    openChatPage.clickIfExistButtonScrollDown(ctx, worker)
                    timeoutService.timeout(TimeoutReasonType.AFTER_OPEN_CHAT_AND_AFTER_SCROLL_DOWN)

                    val readMessages = openChatPage.readMessages(ctx, worker, chatId)
                    if (readMessages.isNotEmpty()) {
                        readMessages.forEach {
                            log.info { "Message: chatId=${it.chatId}, personId=${it.personId} , text=${it.text} , sendAt=${it.sentAt}" }
                        }
                        telegramMessageProducer.sendTelegramMessages(ctx, readMessages)
                    }
                }
            }
        }
    }
}