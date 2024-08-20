package ru.isinsmartsoft.tgcrawlerselenium.tools.task_queue

abstract class TaskWithPriority(
    val priority: PriorityOfTaskEnum
) : Runnable