package ru.isinsmartsoft.tgcrawlerselenium.service.control_panel

import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.config.business.CrawlerProperties
import java.util.concurrent.atomic.AtomicLong

private const val defaultValueReadingInterval = Long.MAX_VALUE

@Service
class CrawlerControlPanelPropertiesImpl(
    private val readingIntervalProperties: CrawlerProperties
) : CrawlerControlPanelProperties {

    @Volatile
    private var readingInterval: AtomicLong = AtomicLong(defaultValueReadingInterval)

    @Volatile
    private var auditWorkerIntervalMilliseconds: AtomicLong = AtomicLong(defaultValueReadingInterval)

    override fun getReadingIntervalSeconds(): Long {
        return if (readingInterval.get() == defaultValueReadingInterval) {
            readingIntervalProperties.readingIntervalSeconds
        } else {
            readingInterval.get()
        }
    }

    override fun editReadingIntervalSeconds(readingInterval: Long) {
        this.readingInterval.set(readingInterval)
    }

    //

    override fun getAuditWorkerIntervalMilliseconds(): Long {
        return if (auditWorkerIntervalMilliseconds.get() == defaultValueReadingInterval) {
            readingIntervalProperties.auditWorkerIntervalMilliseconds
        } else {
            auditWorkerIntervalMilliseconds.get()
        }
    }

    override fun editAuditWorkerIntervalMilliseconds(workerAuditIntervalMilliseconds: Long) {
        this.auditWorkerIntervalMilliseconds.set(workerAuditIntervalMilliseconds)
    }
}