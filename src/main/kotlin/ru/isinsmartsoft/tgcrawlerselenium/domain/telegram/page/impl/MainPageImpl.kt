package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.impl

import io.github.oshai.kotlinlogging.KotlinLogging
import org.openqa.selenium.By
import org.openqa.selenium.interactions.Actions
import org.springframework.stereotype.Component
import ru.isinsmartsoft.tgcrawlerselenium.config.business.TelegramProperties
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerPageState
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.constants.TagTG
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.MainPage
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext
import ru.isinsmartsoft.tgcrawlerselenium.tools.telegram.TelegramCanvasTool

@Component
class MainPageImpl(
    private val telegramProperties: TelegramProperties
) : MainPage {

    private val log = KotlinLogging.logger {}
    override fun open(ctx: AppContext, worker: Worker) {
        try {
            worker.driver.let { driver ->
                driver.get(telegramProperties.url)
                driver.navigate().refresh()
                driver.navigate().to(telegramProperties.url)
            }
            worker.pageState = WorkerPageState.MAIN_PAGE
            log.info { "Worker id = ${worker.id} | Open 'MainPage'" }
        } catch (e: Exception) {
            log.error { "Worker id = ${worker.id} | Open 'MainPage' => ${e.message}" }
            throw e;
        }
    }

    override fun getQRCode(ctx: AppContext, worker: Worker): ByteArray {
        try {
            return worker.driver.let { driver ->
                val actions = Actions(driver)
                val canvas = driver.findElement(By.tagName("canvas"))
                actions.moveToElement(canvas).clickAndHold().perform()
                TelegramCanvasTool.getCanvasAsByteArray(driver, canvas)
            }
        } catch (e: Exception) {
            log.error { "Worker id = ${worker.id} | Get QRCode => ${e.message}" }
            throw e;
        }
    }

    override fun actionClickButtonLoginByPhoneNumber(ctx: AppContext, worker: Worker) {
        try {
            worker.driver
                .findElement(TagTG.A_BUTTON_PHONE_NUMBER)
                .click()
            log.info { "Worker id = ${worker.id} | Action Click Button Login By Phone Number" }

        } catch (e: Exception) {
            log.error { "Worker id = ${worker.id} | Action Click Button Login By Phone Number => ${e.message}" }
            throw e;
        }
    }
}