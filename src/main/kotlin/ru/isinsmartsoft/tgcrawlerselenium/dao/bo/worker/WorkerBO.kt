package ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker

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

val OPTIONS = ChromeOptions()
var seleniumProdGridUrl = "http://31.129.97.175:4444/wd/hub"

class Worker(
    val tgCredentials: TelegramAccountCredentialsBO,
    var state: AbstractWorkerState = NoneWorkerState(),
    var pageState: WorkerPageState = WorkerPageState.NONE
) {
    val id: UUID = UUID.randomUUID()

    val driver: WebDriver

    init {
        driver = ChromeDriver()
        val cap = DesiredCapabilities()

        cap.setCapability(ChromeOptions.CAPABILITY, OPTIONS)
        cap.browserName = "chrome"

        //driver = RemoteWebDriver(URL(seleniumProdGridUrl), cap);
    }

    override fun toString(): String {
        return "Worker(id=$id , phoneNumber=${tgCredentials.phoneNumber}, pageState=$pageState, state=$state)"
    }
}