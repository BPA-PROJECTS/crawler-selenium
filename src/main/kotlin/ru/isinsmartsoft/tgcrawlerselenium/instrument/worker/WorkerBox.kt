package ru.isinsmartsoft.tgcrawlerselenium.instrument.worker

import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerBoxStatusEnum

class WorkerBox(
    val worker: Worker,
    val status: WorkerBoxStatusEnum,
    val queue: WorkerTaskQueue
) {

}