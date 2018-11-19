package com.dobrowins.arrowktplayground.di.providers

import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author: Artem Dobrovinsky
 */
class DispatchersProviderImpl @Inject constructor(): DispatchersProvider {

	override val main: CoroutineContext
		get() = Dispatchers.Main

	override val io: CoroutineContext
		get() = Dispatchers.IO

	override val default: CoroutineContext
		get() = Dispatchers.Default

}