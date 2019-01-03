package com.dobrowins.arrowgit.domain

import arrow.data.Validated
import arrow.data.invalid
import arrow.data.valid

/**
 * @author Artem Dobrovinskiy
 */

val validateString: (String, (String) -> Boolean) -> Validated<Exception, String> = { text, λ ->
    when (λ(text)) {
        true -> text.valid()
        false -> IllegalArgumentException("Input is invalid").invalid()
    }
}