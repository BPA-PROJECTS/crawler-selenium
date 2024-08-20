package ru.isinsmartsoft.tgcrawlerselenium.tools.telegram

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import java.util.*

class TelegramCanvasTool {
    companion object {

        fun getCanvasAsByteArray(driver: WebDriver, canvas: WebElement): ByteArray {
            val javascriptExecutor = driver as JavascriptExecutor
            val base64Image = javascriptExecutor.executeScript(
                "return arguments[0].toDataURL('image/png');", canvas
            ).toString()
            try {
                // Убедиться что base64Image начинается с "value:image/png;base64,"
                val prefix = "value:image/png;base64,"
                if (base64Image.startsWith(prefix)) {
                    // Получить часть после запятой
                    val imageData = base64Image.substring(prefix.length)

                    // Декодировать base64
                    return Base64.getDecoder().decode(imageData)
                } else {
                    throw RuntimeException("Base64 не начинается с value:image/png;base64")
                }
            } catch (e: IllegalArgumentException) {
                throw RuntimeException("Произошла ошибка при декодировании base64" + e.message)
            }
        }
    }
}