package com.dobrowins.arrowgit.di.providers

import com.dobrowins.arrowgit.domain.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * @author: Artem Dobrovinsky
 */
class DispatchersProviderImpl @Inject constructor(): DispatchersProvider {
    override val main: CoroutineContext = Dispatchers.Main
    override val io: CoroutineContext = Dispatchers.IO
    override val default: CoroutineContext = Dispatchers.Default
}