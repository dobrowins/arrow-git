package com.dobrowins.arrowgit.domain

import junit.framework.TestCase.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @author Artem Dobrovinskiy
 */
@RunWith(JUnit4::class)
class DomainFunctionsTest {

    private val validateStringFunc: (String) -> Boolean = String::isNotEmpty
    private val invalidString = ""
    private val validString = "teststring"

    @Test
    fun `validateString returns invalid if result doesn't comply with predicate λ`() {
        val result = validateString(invalidString, validateStringFunc)
        assertTrue(result.isInvalid)
    }

    @Test
    fun `validateString returns valid if result comply with predicate λ`() {
        val result = validateString(validString, validateStringFunc)
        assertTrue(result.isValid)
    }
}