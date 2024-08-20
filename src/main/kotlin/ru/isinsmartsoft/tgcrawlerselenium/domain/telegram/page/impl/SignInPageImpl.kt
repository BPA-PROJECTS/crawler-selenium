package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.impl

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.constants.TagTG
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.SignInPage
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

@Service
class SignInPageImpl : SignInPage {

    private val log = KotlinLogging.logger {}
    override fun inputPhoneNumberPageB(ctx: AppContext, worker: Worker, phoneNumber: String) {
        worker.driver.let { driver ->
            val inputPhoneNumber = driver.findElement(TagTG.B_FIELD_PHONE_NUMBER)
            inputPhoneNumber.clear()
            inputPhoneNumber.sendKeys(phoneNumber)
        }
    }

    override fun clickButtonNextPageB(ctx: AppContext, worker: Worker) {
        worker.driver.let { driver ->
            val buttonNext = driver.findElement(TagTG.B_BUTTON_NEXT)
            buttonNext.click()
        }
    }

    override fun inputLoginCodePageC(ctx: AppContext, worker: Worker, loginCode: String) {
        worker.driver.let { driver ->
            val inputCode = driver.findElement(TagTG.C_FIELD_CODE)
            inputCode.clear()
            inputCode.sendKeys(loginCode)
        }
    }

    override fun inputSecretCodePageD(ctx: AppContext, worker: Worker, secretCode: String) {
        log.info { "SignInPage :: try input secretCode = $secretCode" }
        worker.driver.let { driver ->
            val inputSecretCode = driver.findElement(TagTG.D_FIELD_SECRET_CODE)
            inputSecretCode.clear()
            inputSecretCode.sendKeys(secretCode)
        }
        log.info { "SignInPage :: try input secretCode = $secretCode => Success" }
    }

    override fun clickButtonNextPageD(ctx: AppContext, worker: Worker) {
        log.info { "SignInPage :: click button next on page 'secret-code'" }
        worker.driver.let { driver ->
            val buttonNext = driver.findElement(TagTG.D_BUTTON_NEXT)
            buttonNext.click()
        }
        log.info { "SignInPage :: click button next on page 'secret-code' => Success" }
    }
}