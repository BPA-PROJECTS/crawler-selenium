package ru.isinsmartsoft.tgcrawlerselenium.service.telegram.timeout

import org.springframework.stereotype.Service
import ru.isinsmartsoft.tgcrawlerselenium.dao.enums.TimeoutReasonType
import ru.isinsmartsoft.tgcrawlerselenium.tools.ctx.AppContext

@Service
class TimeoutServiceImpl : TimeoutService {

    private val timeoutMap = HashMap<TimeoutReasonType, Long>().apply {
        this[TimeoutReasonType.AFTER_OPEN_CHAT_AND_BEFORE_READ_MESSAGES] = 1000
        this[TimeoutReasonType.AFTER_OPEN_CHAT_AND_AFTER_SCROLL_DOWN] = 300
    }

    override fun timeout(type: TimeoutReasonType) {
        Thread.sleep(getTimeoutValue(type))
    }

    override fun getTimeoutsMap(ctx: AppContext): Map<TimeoutReasonType, Long> {
        return ctx.run("TimeoutService :: Get timeoutsMap") {
            TimeoutReasonType.entries.associateWith { getTimeoutValue(it) }
        }
    }

    override fun editTimeoutsMap(ctx: AppContext, newTimeouts: Map<TimeoutReasonType, Long>) {
        ctx.run("TimeoutService :: Edit timeoutsMap $newTimeouts") {
            newTimeouts.forEach {
                timeoutMap[it.key] = it.value
            }
        }
    }

    private fun getTimeoutValue(type: TimeoutReasonType): Long {
        return timeoutMap[type] ?: 200
    }
}