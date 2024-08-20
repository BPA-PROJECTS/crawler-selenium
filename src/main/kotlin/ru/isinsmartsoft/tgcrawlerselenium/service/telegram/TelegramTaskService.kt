package ru.isinsmartsoft.tgcrawlerselenium.service.telegram

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext
import ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue.TaskWithPriority

interface TelegramTaskService {

    fun createTaskReadChat(ctx: AppContext, worker: Worker, chatId: String): TaskWithPriority
}