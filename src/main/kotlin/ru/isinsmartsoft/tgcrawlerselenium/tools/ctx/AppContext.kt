package ru.isinsmartsoft.tgcrawlerselenium.tools.ctx

import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.model.ExecutionNodeType

interface AppContext {

    fun log(message: String)

    fun startLevel(message: String): AppContext

    fun <T> endLevel(message: String, function: () -> T): T

    fun <T> run(message: String, function: () -> T): T

    fun setExecutionNodeType(type: ExecutionNodeType)
}