package ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1.timeout

import org.springframework.web.bind.annotation.RestController
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1.timeout.EditTimeoutsRequestV1
import ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.timeout.GetTimeoutsResponseV1
import ru.isinsmartsoft.tgcrawlerselenium.api.endpoint.vAdmin.v1.TimeoutAdminEndpoint
import ru.isinsmartsoft.tgcrawlerselenium.service.telegram.timeout.TimeoutService
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContextStandard

@RestController
class TimeoutAdminEndpointImpl(
    private val timeoutService: TimeoutService
) : TimeoutAdminEndpoint {

    override fun getTimeoutMap(): GetTimeoutsResponseV1 {
        val ctx = AppContextStandard()
        val timeoutsMap = timeoutService.getTimeoutsMap(ctx)
        return GetTimeoutsResponseV1(timeoutsMap)
    }

    override fun editTimeouts(request: EditTimeoutsRequestV1): GetTimeoutsResponseV1 {
        val ctx = AppContextStandard().startLevel("TimeoutAdminEndpoint :: Edit timeouts request=$request")
        timeoutService.editTimeoutsMap(ctx, request.timeouts)
        val timeoutsMap = timeoutService.getTimeoutsMap(ctx)
        return ctx.endLevel("TimeoutAdminEndpoint :: Edit timeouts => OK") { GetTimeoutsResponseV1(timeoutsMap) }
    }
}