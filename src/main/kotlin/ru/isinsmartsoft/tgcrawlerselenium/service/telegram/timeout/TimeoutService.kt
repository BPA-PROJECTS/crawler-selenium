package ru.isinsmartsoft.tgcrawlerselenium.service.telegram.timeout

import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.TimeoutReasonType

interface TimeoutService {

    fun timeout(type: TimeoutReasonType)
    fun getTimeoutsMap(): Map<TimeoutReasonType, Long>

    fun editTimeoutsMap(timeoutsMap: Map<TimeoutReasonType, Long>)
}