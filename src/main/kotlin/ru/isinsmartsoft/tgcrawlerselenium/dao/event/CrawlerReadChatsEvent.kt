package ru.isinsmartsoft.tgcrawlerselenium.dao.event

import java.time.Instant

data class CrawlerReadChatsEvent(
    val date: Instant = Instant.now(),
)
