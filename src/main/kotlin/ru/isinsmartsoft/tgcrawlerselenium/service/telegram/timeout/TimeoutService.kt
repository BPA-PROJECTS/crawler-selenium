package ru.isinsmartsoft.tgcrawlerselenium.service.telegram.timeout

import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.TimeoutReasonType
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

interface TimeoutService {

    fun timeout(type: TimeoutReasonType)

    fun getTimeoutsMap(ctx: AppContext): Map<TimeoutReasonType, Long>

    fun editTimeoutsMap(ctx: AppContext, timeoutsMap: Map<TimeoutReasonType, Long>)
}