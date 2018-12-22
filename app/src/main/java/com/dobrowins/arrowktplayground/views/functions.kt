package com.dobrowins.arrowktplayground.views

import arrow.data.Validated
import arrow.data.invalid
import arrow.data.valid
import kotlinx.coroutines.Job

/**
 * @author Artem Dobrovinskiy
 */

val validateString: (String, (String) -> Boolean) -> Validated<Exception, String> = { text, λ ->
    when (λ(text)) {
        true -> text.valid()
        false -> IllegalArgumentException("Input is invalid").invalid()
    }
}

fun cancelJob(job: Job): (Throwable) -> Throwable = {
	job.cancel()
	it
}

val mapThrowableMessage: (Throwable) -> String = { it.message ?: "Unknown error" }
