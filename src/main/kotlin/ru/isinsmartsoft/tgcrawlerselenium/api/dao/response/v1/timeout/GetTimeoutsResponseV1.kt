package ru.isinsmartsoft.tgcrawlerselenium.api.dao.response.v1.timeout

import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.TimeoutReasonType

data class GetTimeoutsResponseV1(
    val timeouts: Map<TimeoutReasonType, Long>
)