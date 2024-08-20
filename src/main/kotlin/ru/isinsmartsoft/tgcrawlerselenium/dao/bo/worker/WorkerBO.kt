package ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.TelegramAccountCredentialsBO
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state.AbstractWorkerState
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker_state.NoneWorkerState
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerPageState
import java.util.*

class Worker(
    val tgCredentials: TelegramAccountCredentialsBO,
    var state: AbstractWorkerState = NoneWorkerState(),
    var pageState: WorkerPageState = WorkerPageState.NONE
) {
    val id: UUID = UUID.randomUUID()

    val driver: WebDriver

    init {
        driver = ChromeDriver()
    }

    override fun toString(): String {
        return "Worker(id=$id , phoneNumber=${tgCredentials.phoneNumber}, pageState=$pageState, state=$state)"
    }
}