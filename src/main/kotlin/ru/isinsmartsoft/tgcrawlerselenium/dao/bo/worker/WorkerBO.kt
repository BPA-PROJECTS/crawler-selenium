package ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker

import io.github.oshai.kotlinlogging.KotlinLogging
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.TelegramAccountCredentialsBO
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state.AbstractWorkerState
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state.NoneWorkerState
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerPageState
import java.net.URL
import java.util.UUID

private val log = KotlinLogging.logger {}

val OPTIONS = ChromeOptions()

class Worker(
    val tgCredentials: TelegramAccountCredentialsBO,
    isRemote: Boolean = false,
    seleniumGridUrl: String = "",
) {
    val id: UUID = UUID.randomUUID()
    var state: AbstractWorkerState
    var pageState: WorkerPageState
    val driver: WebDriver

    init {
        this.state = NoneWorkerState()
        this.pageState = WorkerPageState.NONE
        this.driver = buildDriver(isRemote, seleniumGridUrl)
    }

    override fun toString(): String {
        return "Worker(id=$id , phoneNumber=${tgCredentials.phoneNumber}, pageState=$pageState, state=$state)"
    }

    private fun buildDriver(isRemote: Boolean, seleniumGridUrl: String?): WebDriver {
        return if (isRemote) {
            val cap = DesiredCapabilities()

            cap.setCapability(ChromeOptions.CAPABILITY, OPTIONS)
            cap.browserName = "chrome"
            log.info { "Selenium grid url: $seleniumGridUrl" }
            RemoteWebDriver(URL(seleniumGridUrl), cap);
        } else {
            ChromeDriver()
        }
    }
}