package com.dobrowins.arrowktplayground.views

import kotlinx.coroutines.Job

/**
 * @author Artem Dobrovinskiy
 */

fun cancelJob(job: Job): (Throwable) -> Throwable = {
	job.cancel()
	it
}

val mapThrowableMessage: (Throwable) -> String = { t ->
	val cause = t.cause?.toString() ?: "Unknown cause"
	val message = t.message ?: "Unknown error"
	val string = "Cause: $cause, message: $message"
	string
}
