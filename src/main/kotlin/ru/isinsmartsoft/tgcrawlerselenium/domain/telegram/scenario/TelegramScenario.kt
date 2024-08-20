package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.scenario

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

interface TelegramScenario {

    /**
     * После этой операции ждет код.
     */
    fun login(ctx: AppContext, worker: Worker)

    fun authByCode(ctx: AppContext, worker: Worker, loginCode: String)

    fun authBySecretCode(ctx: AppContext, worker: Worker, secretCode: String)
}