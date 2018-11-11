package com.dobrowins.arrowktplayground.views

import arrow.core.Either

/**
 * @author Artem Dobrovinskiy
 */
val isEmpty: (String?) -> Either<Throwable, String> = { s ->
    when (s.isNullOrEmpty()) {
        true -> Either.Left(IllegalArgumentException("parameter can't be empty"))
        false -> Either.Right(s)
    }
}

val mapThrowableMessage: (Throwable) -> String = { it.message.orEmpty() }
