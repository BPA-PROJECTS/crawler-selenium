package ru.isinsmartsoft.tgcrawlerselenium.exception

import org.springframework.http.HttpStatus
import java.util.*

abstract class NotFoundException(
    override val message: String,
    override val code: ExceptionCode,
    override val status: HttpStatus = HttpStatus.NOT_FOUND
) : BaseException(message, code, status) {
}

class DraftWorkerNotFoundException(
    draftWorkerId: UUID
) : NotFoundException("Draft worker with id=$draftWorkerId not found", ExceptionCode.N_1)

class RunWorkerNotFoundException(
    runWorkerId: UUID
) : NotFoundException("Run worker with id=$runWorkerId not found", ExceptionCode.N_1)