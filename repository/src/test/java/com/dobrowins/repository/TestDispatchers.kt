package com.dobrowins.repository

import com.dobrowins.arrowktplayground.domain.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * @author Artem Dobrovinskiy
 */
object TestDispatchers {

    fun provide() = object : DispatchersProvider {
        override val main: CoroutineContext = Dispatchers.Default
        override val io: CoroutineContext = Dispatchers.Default
        override val default: CoroutineContext = Dispatchers.Default
    }

}