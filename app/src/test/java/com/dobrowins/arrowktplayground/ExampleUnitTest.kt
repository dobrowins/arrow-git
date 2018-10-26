package com.dobrowins.arrowktplayground

import arrow.core.Option
import arrow.core.Some
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.lang.Exception

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
}

val breakIfEmpty: () -> Unit = { throw Exception() }
val assertTrue: (Boolean) -> Unit = ::assertTrue

