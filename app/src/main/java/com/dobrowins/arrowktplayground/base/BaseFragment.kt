package com.dobrowins.arrowktplayground.base

import android.os.Bundle
import android.support.design.widget.Snackbar
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
        inflater.inflate(layoutId, container, false)

    fun showSnackbar(root: View): (String) -> Unit = { text ->
        runOnUiThread {
            Snackbar.make(root, text, Snackbar.LENGTH_SHORT).show()
        }
    }

    fun runOnUiThread(λ: () -> Unit) = {
        activity?.runOnUiThread { λ() }
            ?: run { Timber.d("activity is not present") }
    }.invoke()

}