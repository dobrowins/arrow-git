package com.dobrowins.arrowktplayground.views

import arrow.core.Either
import arrow.core.Option
import kotlinx.coroutines.Job

/**
 * @author Artem Dobrovinskiy
 */
val isEmpty: (String?) -> Either<Throwable, String> = { s ->
	when (s.isNullOrEmpty()) {
		true -> Either.Left(IllegalArgumentException("parameter can't be empty"))
		false -> Either.Right(s)
	}
}

fun cancelJob(job: Job): (Throwable) -> Throwable = {
	job.cancel()
	it
}

val mapThrowableMessage: (Throwable) -> String = { it.message ?: "Unknown error" }
