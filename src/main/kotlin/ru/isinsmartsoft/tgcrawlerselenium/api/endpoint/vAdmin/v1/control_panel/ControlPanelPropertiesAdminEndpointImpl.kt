package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1.control_panel

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.*
import ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1.ControlPanelPropertiesAdminEndpoint
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.EndpointConstants
import ru.isinsmartsoft.tgcrawlerselenium.service.control_panel.CrawlerControlPanelPropertiesService

@RestController
@RequestMapping("${EndpointConstants.ADMIN_V1}/control-panel")
class ControlPanelPropertiesAdminEndpointImpl(
    private val crawlerProperties: CrawlerControlPanelPropertiesService
) : ControlPanelPropertiesAdminEndpoint {
    private val log = KotlinLogging.logger {}

    @GetMapping("/property/reading-interval-seconds")
    override fun getPropertyCrawlerReadingIntervalSeconds(): Long {
        return crawlerProperties.getReadingIntervalSeconds()
    }

    @PostMapping("/property/reading-interval-seconds/{readingIntervalSeconds}")
    override fun editPropertyCrawlerReadingIntervalSeconds(@PathVariable readingIntervalSeconds: Long) {
        crawlerProperties.editReadingIntervalSeconds(readingIntervalSeconds)
    }

    @GetMapping("/property/audit-worker-interval-milliseconds")
    override fun getPropertyCrawlerAuditWorkerIntervalMilliseconds(): Long {
        return crawlerProperties.getAuditWorkerIntervalMilliseconds()
    }

    @PostMapping("/property/audit-worker-interval-milliseconds/{auditWorkerIntervalMilliseconds}")
    override fun editPropertyCrawlerAuditWorkerIntervalMilliseconds(@PathVariable auditWorkerIntervalMilliseconds: Long) {
        crawlerProperties.editAuditWorkerIntervalMilliseconds(auditWorkerIntervalMilliseconds)
    }

}