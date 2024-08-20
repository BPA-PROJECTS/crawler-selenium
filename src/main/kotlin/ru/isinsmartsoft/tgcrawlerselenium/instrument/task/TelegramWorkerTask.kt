package ru.isinsmartsoft.tgcrawlerselenium.instrument.task

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue.PriorityOfTaskEnum
import ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue.TaskWithPriority

abstract class TelegramWorkerTask(
    priority: PriorityOfTaskEnum
) : TaskWithPriority(priority) {

    lateinit var worker: Worker
}