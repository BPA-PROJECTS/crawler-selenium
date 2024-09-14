package ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.PriorityBlockingQueue

private val log = KotlinLogging.logger {}

class TaskQueueTool(poolSize: Int, queueSize: Int) {

    // ExecutorService для выполнения задач из очереди
    private val executor: ExecutorService

    // Очередь задач с ожиданием выполнения предыдущей
    private val queue: PriorityBlockingQueue<TaskWithPriority>

    init {
        this.queue = PriorityBlockingQueue<TaskWithPriority>(
            queueSize, Comparator.comparing(TaskWithPriority::priority)
        )
        executor = Executors.newFixedThreadPool(poolSize)
        executor.execute {
            while (true) {
                try {
                    if (!queue.isEmpty()) {
                        val task = queue.take()
                        task.run()
                    }
                } catch (e: Exception) {
                    log.error { "TaskQueueTool :: Error - ${e.message}" }
                }
            }
        }
    }

    /**
     * Метод для добавления задачи в очередь.
     */
    fun addTask(task: TaskWithPriority) {
        if (!containsTask(task)) {
            queue.offer(task)
        }
    }

    fun getTasksCount(): Int {
        return queue.size;
    }

    /**
     * Метод для корректного завершения работы потока и ExecutorService
     */
    fun destroy() {
        executor.shutdownNow()
    }

    //
    private fun containsTask(task: TaskWithPriority): Boolean {
        for (existingTask in queue) {
            if (existingTask.equals(task)) {
                return true
            }
        }
        return false
    }
}