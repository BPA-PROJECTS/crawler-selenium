package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Tag(name = "Admin V1 | Получение и Управление свойствами приложениями")
interface ControlPanelPropertiesAdminEndpoint {

    @Operation(summary = "Получить значение - Интервала чтения чатов")
    fun getPropertyCrawlerReadingIntervalSeconds(): Long

    @Operation(summary = "Изменить значение - Интервала чтения чатов")
    fun editPropertyCrawlerReadingIntervalSeconds(readingIntervalSeconds: Long)

    @Operation(summary = "Получить значение - Интервала аудита проверки worker")
    fun getPropertyCrawlerAuditWorkerIntervalMilliseconds(): Long

    @Operation(summary = "Изменить значение - Интервала аудита проверки worker")
    fun editPropertyCrawlerAuditWorkerIntervalMilliseconds(auditWorkerIntervalMilliseconds: Long)
}