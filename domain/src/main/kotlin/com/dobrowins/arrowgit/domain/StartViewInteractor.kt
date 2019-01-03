package com.dobrowins.arrowgit.domain

import arrow.data.Validated

/**
 * @author Artem Dobrovinskiy
 */
interface StartViewInteractor {
    fun validateInput(input: String): Validated<Exception, String>
}