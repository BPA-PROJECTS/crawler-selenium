package ru.isinsmartsoft.tgcrawlerselenium

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.ApplicationConstants

@EnableKafka
@EnableAsync
@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
    println("=== START v=${ApplicationConstants.VERSION} ===")
}
