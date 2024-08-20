package ru.isinsmartsoft.tgcrawlerselenium.config.app

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.SchedulingConfigurer
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import org.springframework.scheduling.config.ScheduledTaskRegistrar
import java.util.concurrent.Executor


@Configuration
class SchedulerConfiguration : SchedulingConfigurer {
    override fun configureTasks(taskRegistrar: ScheduledTaskRegistrar) {
        taskRegistrar.setScheduler(threadPoolTaskScheduler())
    }

    @Bean
    fun threadPoolTaskScheduler(): Executor {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()
        threadPoolTaskScheduler.setPoolSize(50)
        threadPoolTaskScheduler.setThreadNamePrefix("scheduler-")
        return threadPoolTaskScheduler
    }
}