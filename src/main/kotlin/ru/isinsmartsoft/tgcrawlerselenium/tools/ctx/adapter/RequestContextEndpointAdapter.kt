package ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.adapter

import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContextStandard

interface RequestContextEndpointAdapter {
    fun startEndpoint(inMessage: () -> String): AppContextStandard {
        val ctx = AppContextStandard()
        ctx.startLevel("Endpoint => ${inMessage()}")
        return ctx
    }

    fun startEndpoint(request: Any, inMessage: () -> String): AppContextStandard {
        val ctx = AppContextStandard()
        ctx.startLevel("Endpoint => ${inMessage()}")
        return ctx
    }

    fun <T> endEndpoint(ctx: AppContextStandard, message: String, response: () -> T): T {
        return ctx.endLevel("Endpoint <= $message => Success", response)
    }

    fun <T> endEndpoint(ctx: AppContextStandard, response: () -> T): T {
        return ctx.endLevel("Endpoint <= Request completed => Success", response)
    }
}
