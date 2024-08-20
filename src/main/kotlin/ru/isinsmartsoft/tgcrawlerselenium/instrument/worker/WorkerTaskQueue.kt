package ru.isinsmartsoft.tgcrawlerselenium.instrument.worker

import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.WorkerTaskQueueStatusEnum
import ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue.TaskQueueTool
import ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue.TaskWithPriority

class WorkerTaskQueue {

    private val taskQueue = TaskQueueTool(1, 100)

    @Volatile
    private var status: WorkerTaskQueueStatusEnum = WorkerTaskQueueStatusEnum.NONE

    fun addTask(task: TaskWithPriority) = taskQueue.addTask(task)

    fun getCountTaskInQueue(): Int = taskQueue.getTasksCount()

    fun destroy() = taskQueue.destroy()
}