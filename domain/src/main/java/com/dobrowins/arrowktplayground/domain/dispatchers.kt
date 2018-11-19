package com.dobrowins.arrowktplayground.domain

import kotlin.coroutines.CoroutineContext

/**
 * @author: Artem Dobrovinsky
 */

interface DispatchersProvider {
	val main: CoroutineContext
	val io: CoroutineContext
	val default: CoroutineContext
}