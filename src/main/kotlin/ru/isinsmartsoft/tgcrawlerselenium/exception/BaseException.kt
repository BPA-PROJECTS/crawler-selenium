package ru.isinsmartsoft.tgcrawlerselenium.exception

import org.springframework.http.HttpStatus

abstract class BaseException(
    override val message: String,
    open val code: ExceptionCode,
    open val status: HttpStatus,
) : RuntimeException() {
}

enum class ExceptionCode {
    N_1,
    N_2,
}