package ru.isinsmartsoft.tgcrawlerselenium.tools.date

import java.time.Instant

class DateTool {
    companion object {

        fun now(): Instant {
            return Instant.now()
        }
    }
}