package ru.isinsmartsoft.tgcrawlerselenium.service.telegram

import io.github.oshai.kotlinlogging.KotlinLogging
import org.openqa.selenium.By
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerPageState
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

private val log = KotlinLogging.logger {}

@Service
class WorkerPageStateServiceImpl : WorkerPageStateService {

    override fun determineWorkerPageState(ctx: AppContext, worker: Worker): WorkerPageState {
        val workerPageState = defineWorkerPageState(ctx, worker).apply {
            if (this == WorkerPageState.UNDEFINED) {
                log.warn { "WorkerPageStateService :: define worker page state = $this | Run sleep and redefine" }
                Thread.sleep(200)
                val newWorkerPageState = defineWorkerPageState(ctx, worker)
                log.warn { "WorkerPageStateService :: redefine worker page state = $newWorkerPageState" }
                return newWorkerPageState
            }
        }
        worker.pageState = workerPageState
        return workerPageState
    }

    private fun defineWorkerPageState(ctx: AppContext, worker: Worker): WorkerPageState {
        if (!isAlive(ctx, worker)) return WorkerPageState.NONE

        // Проверка что открыта страница "ввода кода"
        try {
            val isInputCode =
                worker.driver.findElement(By.cssSelector("div.page-authCode")).getAttribute("class").contains("active")
            if (isInputCode) return WorkerPageState.INPUT_CODE_PAGE
        } catch (_: Exception) {
        }
        // Проверка что открыта страница "ввода секретного кода"
        try {
            val isInputCode =
                worker.driver.findElement(By.cssSelector("div.page-password")).getAttribute("class").contains("active")
            if (isInputCode) return WorkerPageState.INPUT_SECRET_CODE_PAGE
        } catch (_: Exception) {
        }
        // Проверка что открыт workspace
        try {
            val isLogin =
                worker.driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div")).getAttribute("class")
                    .contains("active")
            if (isLogin) return WorkerPageState.WORKSPACE_PAGE
        } catch (_: Exception) {
        }
        return WorkerPageState.UNDEFINED
    }

    // ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ
    /**
     * Проверка, что driver в worker является живым.
     */
    private fun isAlive(ctx: AppContext, worker: Worker) = try {
        worker.driver.currentUrl
        true
    } catch (e: Exception) {
        false
    }
}