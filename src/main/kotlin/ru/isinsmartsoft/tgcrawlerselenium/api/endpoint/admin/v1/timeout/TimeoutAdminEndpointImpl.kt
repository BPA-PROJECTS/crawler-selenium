package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.admin.v1.timeout

import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1.timeout.EditTimeoutsRequestV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.timeout.GetTimeoutsResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.admin.v1.TimeoutAdminEndpoint
import ru.isinsmartsoft.tgcrawlerselenium.service.telegram.timeout.TimeoutService

@RestController
class TimeoutAdminEndpointImpl(
    private val timeoutService: TimeoutService
) : TimeoutAdminEndpoint {
    override fun getTimeoutMap(): GetTimeoutsResponseV1 {
        val timeoutsMap = timeoutService.getTimeoutsMap()
        return GetTimeoutsResponseV1(timeoutsMap)
    }

    override fun editTimeouts(request: EditTimeoutsRequestV1): GetTimeoutsResponseV1 {
        timeoutService.editTimeoutsMap(request.timeouts)
        val timeoutsMap = timeoutService.getTimeoutsMap()
        return GetTimeoutsResponseV1(timeoutsMap)
    }
}