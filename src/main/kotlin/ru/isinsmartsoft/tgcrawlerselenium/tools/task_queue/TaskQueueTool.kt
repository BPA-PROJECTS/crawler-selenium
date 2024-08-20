package ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

private val log = KotlinLogging.logger {}

class TaskQueueTool(poolSize: Int, queueSize: Int) {

    // ExecutorService для выполнения задач из очереди
    private val executor: ExecutorService

    // Очередь задач с ожиданием выполнения предыдущей
    private val queue: PriorityBlockingQueue<TaskWithPriority>

    private val taskCount: AtomicInteger

    init {
        this.queue = PriorityBlockingQueue<TaskWithPriority>(
            queueSize, Comparator.comparing(TaskWithPriority::priority)
        )
        executor = Executors.newFixedThreadPool(poolSize)
        taskCount = AtomicInteger(0)
        executor.execute {
            while (true) {
                try {
                    if (!queue.isEmpty()) {
                        val task = queue.take()
                        task.run()
                        taskCount.decrementAndGet()
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
            taskCount.incrementAndGet()
        }
    }

    fun getTasksCount(): Int {
        return taskCount.get()
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