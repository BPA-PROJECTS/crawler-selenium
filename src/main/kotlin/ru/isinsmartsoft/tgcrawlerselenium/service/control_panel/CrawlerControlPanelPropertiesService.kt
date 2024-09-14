package ru.isinsmartsoft.tgcrawlerselenium.service.control_panel

interface CrawlerControlPanelPropertiesService {

    // Взаимодействие с интервалом чтения чатов
    fun getReadingIntervalSeconds(): Long
    fun editReadingIntervalSeconds(readingInterval: Long)

    // Взаимодействие с интервалом проверки worker
    fun getAuditWorkerIntervalMilliseconds(): Long
    fun editAuditWorkerIntervalMilliseconds(workerAuditIntervalMilliseconds: Long)
}