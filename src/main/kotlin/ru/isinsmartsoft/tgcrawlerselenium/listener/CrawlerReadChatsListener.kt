package ru.isinsmartsoft.tgcrawlerselenium.listener

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.event.CrawlerReadChatsEvent
import ru.isinsmartsoft.tgcrawlerselenium.service.observed_chats.ObservedChatService
import ru.isinsmartsoft.tgcrawlerselenium.service.telegram.TelegramTaskService
import ru.isinsmartsoft.tgcrawlerselenium.service.worker.WorkerManagementService
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContextStandard
import java.util.UUID

@Service
class CrawlerReadChatsListener(
    private val observedChatService: ObservedChatService,
    private val workerManagementService: WorkerManagementService,
    private val telegramTaskService: TelegramTaskService
) {
    private val log = KotlinLogging.logger {}

    @Async("threadPoolCrawlerReadChatsExecutor")
    @EventListener(CrawlerReadChatsEvent::class)
    fun onEvent(event: CrawlerReadChatsEvent) {
        val ctx = AppContextStandard()
        ctx.startLevel("CrawlerReadChatsListener :: Анализ наблюдаемых чатов и создание задач на чтение")

        val observedChatIds = observedChatService.getObservedChatIds(ctx)
        val runWorkersBoxMap = workerManagementService.getAllRunWorkersBox(ctx).associateBy { it.worker.id }
        val freeWorkerIds = runWorkersBoxMap.keys.toList()

        if (freeWorkerIds.isEmpty()) {
            return ctx.endLevel("CrawlerReadChatsListener :: Workers not found | ObservedChatIds: $observedChatIds") {}
        }
        if (observedChatIds.isEmpty()) {
            return ctx.endLevel("CrawlerReadChatsListener :: ObservedChatIds not found | FreeWorkerIds: $freeWorkerIds") {}
        }

        val dist = generateDistribution(observedChatIds, freeWorkerIds)
        ctx.log("Dist: $dist")
        dist.forEach { (observedChatId, workerId) ->
            run {
                val workerBox = runWorkersBoxMap[workerId]!!
                workerBox.queue.addTask(
                    telegramTaskService.createTaskReadChat(ctx.copy(), workerBox.worker, observedChatId)
                )
            }
        }
        ctx.endLevel("CrawlerReadChatsListener :: Processing event => Success") {}
    }

    private fun generateDistribution(
        observedChatIds: Set<String>, freeWorkerIds: List<UUID>
    ): Set<Pair<String, UUID>> {
        var k = 0
        val dist = mutableSetOf<Pair<String, UUID>>()
        observedChatIds.forEach { observedChatId ->
            val pair = Pair(observedChatId, freeWorkerIds[k++])
            if (k == freeWorkerIds.size) {
                k = 0
            }
            dist.add(pair)
        }
        return dist
    }
}