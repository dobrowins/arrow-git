package com.dobrowins.arrowktplayground

import arrow.core.Option
import arrow.core.Some
import arrow.effects.IO
import arrow.instances.id.applicative.just
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.lang.Exception
import java.lang.IllegalArgumentException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

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

