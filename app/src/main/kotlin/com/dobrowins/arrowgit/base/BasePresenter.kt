package com.dobrowins.arrowgit.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * @author Artem Dobrovinskiy
 */
abstract class BasePresenter<V : MvpView> : MvpPresenter<V>(), CoroutineScope {

    private val supervisorJob = SupervisorJob()

    override var coroutineContext: CoroutineContext = Dispatchers.Main + supervisorJob

    val addJobToScope: (Job) -> Unit = { coroutineContext + it }

    fun Job.addToScope() {
        coroutineContext + this
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineContext.cancelChildren()
    }
}