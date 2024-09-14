package ru.isinsmartsoft.tgcrawlerselenium.config.configurations.app

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebMvcConfiguration : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept")
            .maxAge(3600) // Max age for caching the pre-flight response is 1 hour
    }

}
