package ru.isinsmartsoft.tgcrawlerselenium.config.properties.business

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "appsettings.crawler")
class CrawlerProperties(
    // Интервал в секундах
    val readingIntervalSeconds: Long,

    // Интервал проверки состояния worker в миллисекундах
    val auditWorkerIntervalMilliseconds: Long
)
