package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1.worker

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1.WorkerManagementAdminEndpoint
import ru.isinsmartsoft.tgcrawlerselenium.config.constants.EndpointConstants
import ru.isinsmartsoft.tgcrawlerselenium.service.worker.WorkerManagementService

@RestController
@RequestMapping("${EndpointConstants.ADMIN_V1}/workers/management")
class WorkerManagementAdminEndpointImpl(
    private val workerManagementService: WorkerManagementService
) : WorkerManagementAdminEndpoint {

}