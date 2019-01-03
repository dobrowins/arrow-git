package com.dobrowins.arrowgit.domain

import arrow.data.Validated
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
class StartViewInteractorImpl @Inject constructor() : StartViewInteractor {

    override fun validateInput(input: String): Validated<Exception, String> =
        validateString(input, String::isNotEmpty)

}