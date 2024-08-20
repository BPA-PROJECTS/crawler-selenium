package ru.isinsmartsoft.tgcrawlerselenium.config.app

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.ApplicationConstants
import ru.isinsmartsoft.tgcrawlerselenium.constants.EndpointConstants


@Configuration
class SwaggerConfiguration {
    @Bean
    fun microserviceOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info().title("API TG Crawler Selenium")
                    .description("API управления crawler-selenium для телеграмма")
                    .version(ApplicationConstants.VERSION)
            )
    }

    @Bean
    fun api(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .displayName("ALL")
            .group("ALL")
            .pathsToMatch("/**")
            .build()
    }

    @Bean
    fun apiAdminV1(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .displayName("Admin V1")
            .group("Admin V1")
            .pathsToMatch(EndpointConstants.ADMIN_V1 + "/**")
            .build()
    }

    @Bean
    fun apiV1(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .displayName("V1")
            .group("V1")
            .pathsToMatch(EndpointConstants.V1 + "/**")
            .build()
    }
}
