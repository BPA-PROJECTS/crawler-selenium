package ru.isinsmartsoft.tgcrawlerselenium.scheduler

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.ApplicationEventPublisher
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.event.AuditWorkerEvent
import ru.isinsmartsoft.tgcrawlerselenium.dao.event.CrawlerReadChatsEvent
import ru.isinsmartsoft.tgcrawlerselenium.service.control_panel.CrawlerControlPanelPropertiesService
import ru.isinsmartsoft.tgcrawlerselenium.tools.date.DateTool
import java.time.Instant


@Service
@ConditionalOnProperty(
    name = ["appsettings.system.scheduler.crawler-read-chats"],
    havingValue = "true",
    matchIfMissing = false
)
class TaskManagerScheduler(
    private val crawlerProperties: CrawlerControlPanelPropertiesService,
    private val publisher: ApplicationEventPublisher,
) {
    private val log = KotlinLogging.logger {}

    private var lastDateStartReadChats = DateTool.now()

    private var lastDateAuditWorker = DateTool.now()

    @Scheduled(fixedDelay = 500)
    fun taskManager() {
        val dateNow = DateTool.now()
        processingCrawlerReadChatsEvent(dateNow)
        processingAuditWorker(dateNow)
    }

    private fun processingCrawlerReadChatsEvent(dateNow: Instant) {
        if (dateNow.isAfter(lastDateStartReadChats.plusSeconds(crawlerProperties.getReadingIntervalSeconds()))) {
            log.trace { "TaskManagerScheduler :: send event CrawlerReadChatsEvent" }
            publisher.publishEvent(CrawlerReadChatsEvent())
            lastDateStartReadChats = dateNow
        }
    }

    private fun processingAuditWorker(dateNow: Instant) {
        if (dateNow.isAfter(lastDateAuditWorker.plusMillis(crawlerProperties.getAuditWorkerIntervalMilliseconds()))) {
            log.trace { "TaskManagerScheduler :: send event CrawlerReadChatsEvent" }
            publisher.publishEvent(object : AuditWorkerEvent {})
            lastDateAuditWorker = dateNow
        }
    }
}