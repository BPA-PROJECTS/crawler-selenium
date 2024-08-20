package ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.impl

import io.github.oshai.kotlinlogging.KotlinLogging
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.dto.telegram.TelegramMessageDTO
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.constants.TagTG
import ru.isinsmartsoft.tgcrawlerselenium.domain.telegram.page.OpenChatPage
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext
import ru.isinsmartsoft.tgcrawlerselenium.tools.date.DateTool
import ru.isinsmartsoft.tgcrawlerselenium.tools.telegram.TelegramUrlTool
import java.time.Instant

@Service
class OpenChatPageImpl : OpenChatPage {

    private val log = KotlinLogging.logger {}

    override fun openChat(ctx: AppContext, worker: Worker, chatId: String) {
        ctx.run("OpenChatPage :: Open chat=$chatId by worker=${worker.id} [${worker.state.status}]") {
            worker.driver.let { driver ->
                driver.navigate().refresh()
                driver.navigate().to(TelegramUrlTool.buildUrl(chatId))
            }
        }
    }

    override fun readMessages(ctx: AppContext, worker: Worker, chatId: String): List<TelegramMessageDTO> {
        // Скрол вниз по чату
        val driver = worker.driver
        val messages = mutableListOf<TelegramMessageDTO>()
        try {
            val elements = driver
                .findElements(By.cssSelector("div.bubbles-group"))
                .mapNotNull {
                    val content = it.findElement(By.cssSelector("div.bubble-content"))
                    val avatar = it.findElement(By.cssSelector("div.avatar"))
                    val metaInfo = it.findElement(By.cssSelector("div.bubble"))
                    try {
                        val text = content.findElement(By.cssSelector("div.spoilers-container")).text
                        val dateSeconds = metaInfo.getAttribute("data-timestamp")
                        val date = Instant.ofEpochSecond(dateSeconds.toLong())
                        val lines = text.split("\n")
                        if (lines.size <= 1) return@mapNotNull null
                        val resultText = lines.subList(0, lines.size - 1).joinToString("\n")
                        val personId = definePersonIdFromMessage(avatar, chatId)
                        TelegramMessageDTO(personId, chatId, resultText, date, DateTool.now())
                    } catch (e: Exception) {
                        log.error { "UNEXPECTED ERROR then parse partMessage | Error: $e" }
                        return@mapNotNull null
                    }
                }
            messages.addAll(elements)
        } catch (e: Exception) {
            log.error { "UNEXPECTED ERROR then read messages | Error: $e" }
        }
        return messages
    }

    override fun clickButtonScrollDown(ctx: AppContext, worker: Worker) {
        ctx.run("OpenChatPage :: click button scroll down") {
            worker.driver.findElement(TagTG.P_05_BUTTON_SCROLL_DOWN).click()
        }
    }

    override fun clickIfExistButtonScrollDown(ctx: AppContext, worker: Worker) {
        ctx.startLevel("OpenChatPage :: Click button scroll down if exist")
        val element = try {
            worker.driver.findElement(TagTG.P_05_BUTTON_SCROLL_DOWN)
        } catch (e: Exception) {
            return ctx.endLevel("OpenChatPage :: Button not found") {}
        }
        element.click()
        return ctx.endLevel("OpenChatPage :: Click button scroll down => OK") {}
    }

    // ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ
    private fun definePersonIdFromMessage(message: WebElement, chatId: String): String {
        return try {
            message.getAttribute("data-peer-id")
        } catch (e: java.lang.Exception) {
            chatId
        }
    }

}