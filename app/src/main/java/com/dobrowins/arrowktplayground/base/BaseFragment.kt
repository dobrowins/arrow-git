package com.dobrowins.arrowktplayground.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dobrowins.arrowktplayground.di.Scopes
import toothpick.Toothpick

/**
 * @author: Artem Dobrovinsky
 */
abstract class BaseFragment : Fragment() {

	abstract val layoutId: Int

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
        Toothpick.openScope(Scopes.FRAGMENT)
            .also { scope ->
                Toothpick.inject(this, scope)
                Toothpick.closeScope(scope.name)
            }
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
		inflater.inflate(layoutId, container, false)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

	}

}