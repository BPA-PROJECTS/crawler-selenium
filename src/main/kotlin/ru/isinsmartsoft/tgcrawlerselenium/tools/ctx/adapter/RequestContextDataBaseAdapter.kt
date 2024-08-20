package ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.adapter

import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.model.ExecutionNodeType

interface RequestContextDataBaseAdapter {

    fun <T> contextWithRequestToDataBase(ctx: AppContext, function: () -> T): T {
        ctx.setExecutionNodeType(ExecutionNodeType.DATABASE)
        return function()
    }
}
