package ru.isinsmartsoft.tgcrawlerselenium.api.mapper.v1

import org.springframework.stereotype.Component
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.worker.GetWorkersResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.worker.WorkerResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.worker.WorkerTaskQueueResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.dao.bo.worker.Worker
import ru.isinsmartsoft.tgcrawlerselenium.instrument.worker.WorkerBox

@Component
class WorkerMapperV1 {

    fun mapToGetWorkersResponseV1FromWorkers(workers: List<Worker>): GetWorkersResponseV1 {
        return GetWorkersResponseV1(
            data = workers.map { worker ->
                WorkerResponseV1(
                    workerId = worker.id,
                    status = worker.state.status,
                    pageState = worker.pageState,
                    taskQueue = WorkerTaskQueueResponseV1(
                        count = -1
                    )
                )
            }
        )
    }

    fun mapToGetWorkersResponseV1(workersBox: List<WorkerBox>): GetWorkersResponseV1 {
        return GetWorkersResponseV1(
            data = workersBox.map { workerBox ->
                val worker = workerBox.worker
                WorkerResponseV1(
                    workerId = worker.id,
                    status = worker.state.status,
                    pageState = worker.pageState,
                    taskQueue = WorkerTaskQueueResponseV1(
                        count = workerBox.queue.getCountTaskInQueue()
                    )
                )
            }
        )
    }
}