package com.dobrowins.arrowgit.views

import com.dobrowins.arrowgit.base.adapters.HolderType
import com.dobrowins.arrowgit.views.repos.ErrorItem
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

val mapToErrorItem: (String) -> List<HolderType> =
    { message -> listOf(ErrorItem(message = message)) }
