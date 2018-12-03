package com.dobrowins.arrowktplayground

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import arrow.core.fix
import arrow.data.Kleisli
import arrow.data.Validated
import arrow.data.invalid
import arrow.data.valid
import arrow.effects.IO
import junit.framework.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun kleisliTest() {
        val optionIntKleisli = Kleisli { str: String ->
            if (str.toCharArray().all { it.isDigit() }) Some(str.toInt()) else None
        }

        fun String.safeToInt(): Option<Int> {
            return optionIntKleisli.run(this).fix()
        }
    }

    @Test
    fun validatedTest() {
        val assertEmail: (String) -> Validated<IllegalArgumentException, String> = { input ->
            when (input.contains("@gmail.com")) {
                true -> input.valid()
                false -> IllegalArgumentException("input is not email").invalid()
            }
        }

        val validEmail = "one@gmail.com"
        val invalidEmail = "one@mail.ru"
        assertTrue(assertEmail(validEmail).isValid)
        assertTrue(assertEmail(invalidEmail).isInvalid)
    }

	@Test
	fun optionTest() {
		val actualOption: Option<Boolean> = Some(true)
		actualOption.fold(breakIfEmpty, assertTrue)

	}

	@Test
	fun IOtest() {
		val yeldInt: () -> Int = { 1 }
		val throwException: () -> Throwable = { TestException("throwException lambda is throwing exception; go figure") }
		val nonEmptyIO = composeIO<Int>()(yeldInt)
		val errorIO = IO { throw IllegalArgumentException() }
		nonEmptyIO.unsafeRunAsync {
			it
		}
		errorIO.unsafeRunAsync {
			it
		}

	}
}

val breakIfEmpty: () -> Unit = { throw Exception() }
val assertTrue: (Boolean) -> Unit = ::assertTrue
fun <T> composeIO(): (f: () -> T) -> IO<T> = { f ->
	IO.just(f())
}

class TestException(message: String?): Throwable()

