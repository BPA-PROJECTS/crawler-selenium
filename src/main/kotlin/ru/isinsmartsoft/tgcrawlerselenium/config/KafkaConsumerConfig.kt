package ru.isinsmartsoft.tgcrawlerselenium.config

import io.github.oshai.kotlinlogging.KotlinLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@Configuration
class KafkaConsumerConfig(
    @Value("\${spring.kafka.bootstrap-servers}")
    private val bootstrapAddress: String
) {
    companion object {
        const val DLT_TOPIC_SUFFIX = ".dlt"
    }

    private val log = KotlinLogging.logger {}

    @Bean
    fun consumerFactory(): ConsumerFactory<String, String> {
        val configProps: MutableMap<String, Any> = HashMap()
        configProps[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        configProps[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        configProps[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        return DefaultKafkaConsumerFactory(configProps)
    }

    @Bean
    fun eventsKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        // Позволяет создавать консюмеров, которые могут обрабатывать сообщения из нескольких партиций Kafka одновременно,
        // а также настраивать параметры такие как количество потоков, хэндлинг и т.д.
        val kafkaListenerContainerFactory = ConcurrentKafkaListenerContainerFactory<String, String>()
        // Настройка фабрики для создания консьюмера Kafka
        kafkaListenerContainerFactory.consumerFactory = consumerFactory()
        return kafkaListenerContainerFactory
    }
}
