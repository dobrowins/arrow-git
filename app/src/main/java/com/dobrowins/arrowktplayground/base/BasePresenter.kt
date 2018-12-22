package com.dobrowins.arrowktplayground.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * @author Artem Dobrovinskiy
 */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V>(), CoroutineScope {

    private val supervisorJob = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + supervisorJob

    val addJobToScope: (Job) -> Unit = { coroutineContext + it }

    fun Job.addToScope() {
        coroutineContext + this
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}