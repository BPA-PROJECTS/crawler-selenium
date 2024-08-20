package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

interface MainPage {

    fun open(ctx: AppContext, worker: Worker)

    fun getQRCode(ctx: AppContext, worker: Worker): ByteArray

    fun actionClickButtonLoginByPhoneNumber(ctx: AppContext, worker: Worker)
}