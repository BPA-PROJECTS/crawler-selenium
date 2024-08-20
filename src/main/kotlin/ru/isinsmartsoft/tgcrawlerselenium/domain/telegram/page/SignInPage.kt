package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

interface SignInPage {

    fun inputPhoneNumberPageB(ctx: AppContext, worker: Worker, phoneNumber: String)

    fun clickButtonNextPageB(ctx: AppContext, worker: Worker)

    fun inputLoginCodePageC(ctx: AppContext, worker: Worker, loginCode: String)

    fun inputSecretCodePageD(ctx: AppContext, worker: Worker, secretCode: String)

    fun clickButtonNextPageD(ctx: AppContext, worker: Worker)
}