package com.dobrowins.arrowktplayground

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author: Artem Dobrovinsky
 */
interface DispatchersProvider {
	val main: CoroutineContext
	val io: CoroutineContext
	val default: CoroutineContext
}

class DispatchersProviderImpl @Inject constructor(): DispatchersProvider {

	override val main: CoroutineContext
		get() = Dispatchers.Main

	override val io: CoroutineContext
		get() = Dispatchers.IO

	override val default: CoroutineContext
		get() = Dispatchers.Default

}