package com.dobrowins.arrowktplayground.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dobrowins.arrowktplayground.di.Scopes
import timber.log.Timber
import toothpick.Toothpick

/**
 * @author: Artem Dobrovinsky
 */
abstract class BaseFragment : Fragment() {

	abstract val layoutId: Int

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
        Toothpick.openScopes(Scopes.APPLICATION, Scopes.FRAGMENT)
            .also { scope ->
                Toothpick.inject(this, scope)
                Toothpick.closeScope(scope.name)
            }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
		inflater.inflate(layoutId, container, false)

    fun runOnUiThread(f: () -> Unit): Unit = {
        activity?.runOnUiThread {
            f.invoke()
        } ?: run { Timber.d("activity is not present") }
    }.invoke()

}