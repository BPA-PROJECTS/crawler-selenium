package ru.isinsmartsoft.tgcrawlerselenium.integration.kafka

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.KafkaConstants
import ru.isinsmartsoft.tgcrawlerselenium.dao.dto.telegram.TelegramMessageDTO
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext
import ru.isinsmartsoft.tgcrawlerselenium.tools.json.MapperTool

@Service
class TelegramMessageProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    private val log = KotlinLogging.logger {}

    fun sendTelegramMessages(ctx: AppContext, readMessages: List<TelegramMessageDTO>) {
        ctx.startLevel("TelegramMessageProducer :: Send read telegram messages")
        kafkaTemplate.send(KafkaConstants.TOPIC_MESSAGES, MapperTool.mapper.writeValueAsString(readMessages))
        ctx.endLevel("TelegramMessageProducer :: Send telegram messages , count = ${readMessages.size} } => Success") {}
    }
}