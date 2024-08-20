package ru.isinsmartsoft.tgcrawlerselenium.dao.dto.telegram

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.Instant

data class TelegramMessageDTO(
    @JsonProperty("personId")
    val personId: String,

    @JsonProperty("chatId")
    val chatId: String,

    @JsonProperty("text")
    val text: String,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    val sentAt: Instant,

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    val readAt: Instant,
)