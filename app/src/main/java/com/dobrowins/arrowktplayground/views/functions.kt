package com.dobrowins.arrowktplayground.views

import arrow.data.Validated
import arrow.data.invalid
import arrow.data.valid
import kotlinx.coroutines.Job

/**
 * @author Artem Dobrovinskiy
 */
val validateString: (String, (String) -> Boolean) -> Validated<Exception, String> = { email, f ->
    when (f(email)) {
        true -> email.valid()
        false -> IllegalArgumentException("email is invalid").invalid()
    }
}

fun cancelJob(job: Job): (Throwable) -> Throwable = {
	job.cancel()
	it
}

val mapThrowableMessage: (Throwable) -> String = { it.message ?: "Unknown error" }
