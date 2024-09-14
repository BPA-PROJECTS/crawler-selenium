package ru.isinsmartsoft.tgcrawlerselenium.config.properties.business

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "appsettings.telegram")
class TelegramProperties(
    val url: String
)