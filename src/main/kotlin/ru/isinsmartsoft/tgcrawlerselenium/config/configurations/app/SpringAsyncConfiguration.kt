package ru.isinsmartsoft.tgcrawlerselenium.config.configurations.app

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler
import java.util.concurrent.Executor


@Configuration
class SpringAsyncConfiguration {

    @Bean(name = ["threadPoolCrawlerReadChatsExecutor"])
    fun threadPoolCrawlerReadChatsExecutor(): Executor {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()
        threadPoolTaskScheduler.setPoolSize(1)
        threadPoolTaskScheduler.setThreadNamePrefix("tg-read-pool-")
        return threadPoolTaskScheduler
    }

    @Bean(name = ["threadPoolAuditWorkerExecutor"])
    fun threadPoolAuditWorkerExecutor(): Executor {
        val threadPoolTaskScheduler = ThreadPoolTaskScheduler()
        threadPoolTaskScheduler.setPoolSize(1)
        threadPoolTaskScheduler.setThreadNamePrefix("tg-audit-pool-")
        return threadPoolTaskScheduler
    }
}