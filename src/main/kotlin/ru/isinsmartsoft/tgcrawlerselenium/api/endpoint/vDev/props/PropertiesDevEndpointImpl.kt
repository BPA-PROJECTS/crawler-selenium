package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vDev.props

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vDev.PropertiesDevEndpoint
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.EndpointConstants
import ru.isinsmartsoft.tgcrawlerselenium.config.properties.business.SystemSeleniumProperties

@RestController
@RequestMapping(EndpointConstants.V_DEV)
class PropertiesDevEndpointImpl(
    private val systemSeleniumProperties: SystemSeleniumProperties
) : PropertiesDevEndpoint {

    override fun getProperties(): String {
        return """
        Selenium:
            gridUrl = ${systemSeleniumProperties.gridUrl}
            isRemote = ${systemSeleniumProperties.isRemote}
        """.trimIndent()
    }

}