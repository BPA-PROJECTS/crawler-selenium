package ru.isinsmartsoft.tgcrawlerselenium.tools.ctx

import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.model.ExecutionNodeType
import java.util.UUID

interface AppContext {
    val requestId: UUID

    fun log(message: String)

    fun startLevel(message: String): AppContext

    fun <T> endLevel(message: String, function: () -> T): T

    fun <T> run(message: String, function: () -> T): T

    fun setExecutionNodeType(type: ExecutionNodeType)

    fun copy(): AppContext {
        return AppContextStandard(this.requestId)
    }
}