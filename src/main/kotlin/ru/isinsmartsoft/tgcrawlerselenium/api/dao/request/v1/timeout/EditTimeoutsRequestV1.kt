package ru.isinsmartsoft.tgcrawlerselenium.api.dao.request.v1.timeout

import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.TimeoutReasonType

data class EditTimeoutsRequestV1(
    val timeouts: Map<TimeoutReasonType, Long>
)
