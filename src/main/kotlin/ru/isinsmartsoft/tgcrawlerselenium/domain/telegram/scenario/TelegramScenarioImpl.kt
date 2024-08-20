package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.scenario

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerPageState
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.MainPage
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.SignInPage
import ru.isinsmartsoft.tgcrawlerselenium.service.telegram.WorkerPageStateService
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

@Service
class TelegramScenarioImpl(
    private val mainPage: MainPage,
    private val signInPage: SignInPage,
    private val workerPageStateService: WorkerPageStateService
) : TelegramScenario {

    private val log = KotlinLogging.logger {}
    override fun login(ctx: AppContext, worker: Worker) {
        log.info { "TelegramScenario :: login | workerId=${worker.id}" }
        mainPage.open(ctx, worker)
        Thread.sleep(1000)
        mainPage.actionClickButtonLoginByPhoneNumber(ctx, worker)
        Thread.sleep(1000)
        signInPage.inputPhoneNumberPageB(ctx, worker, worker.tgCredentials.phoneNumber)
        Thread.sleep(1000)
        signInPage.clickButtonNextPageB(ctx, worker)
        log.info { "TelegramScenario :: login | workerId=${worker.id} => Success" }
    }

    override fun authByCode(ctx: AppContext, worker: Worker, loginCode: String) {
        log.info { "TelegramScenario :: auth by login code | workerId=${worker.id} | loginCode=$loginCode" }
        signInPage.inputLoginCodePageC(ctx, worker, loginCode)
        if (workerPageStateService.determineWorkerPageState(ctx, worker) == WorkerPageState.INPUT_SECRET_CODE_PAGE) {
            Thread.sleep(1000)
            signInPage.inputSecretCodePageD(ctx, worker, worker.tgCredentials.secretCode)
            Thread.sleep(500)
            signInPage.clickButtonNextPageD(ctx, worker)
        }
        log.info { "TelegramScenario :: auth by login code => Success" }
    }

    override fun authBySecretCode(ctx: AppContext, worker: Worker, secretCode: String) {
        log.info { "TelegramScenario :: auth by secret code | workerId=${worker.id} | secretCode=$secretCode" }
        signInPage.inputSecretCodePageD(ctx, worker, secretCode)
        Thread.sleep(500)
        signInPage.clickButtonNextPageD(ctx, worker)
        log.info { "TelegramScenario :: auth by secret code => Success" }
    }
}