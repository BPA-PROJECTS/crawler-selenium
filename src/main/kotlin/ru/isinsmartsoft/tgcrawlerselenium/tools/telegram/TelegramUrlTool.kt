package ru.isinsmartsoft.tgcrawlerselenium.tools.telegram


class TelegramUrlTool {
    companion object {
        const val ROOT_CHAT_ID = "777000"

        const val BASE_URL = "https://web.telegram.org/k/#"
        const val MAIN_TG_CHAT_URL = BASE_URL + ROOT_CHAT_ID


        fun buildUrl(chatId: String): String = BASE_URL + chatId
    }
}