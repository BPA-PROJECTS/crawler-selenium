package ru.isinsmartsoft.tgcrawlerselenium.config.properties.business

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "appsettings.system.selenium")
class SystemSeleniumProperties(
    // Управление тем какой драйвер будет использоваться локальный или удаленный
    val isRemote: Boolean,
    val gridUrl: String,
)