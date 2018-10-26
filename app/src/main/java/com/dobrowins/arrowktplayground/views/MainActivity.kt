package com.dobrowins.arrowktplayground.views

import android.os.Bundle
import com.dobrowins.arrowktplayground.R.id
import com.dobrowins.arrowktplayground.R.layout
import com.dobrowins.arrowktplayground.base.BaseActivity
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import com.dobrowins.arrowktplayground.navigatorHolder
import com.dobrowins.arrowktplayground.toast
import ru.terrakok.cicerone.android.SupportFragmentNavigator

class MainActivity : BaseActivity() {

	private val navigator: SupportFragmentNavigator =
		object : SupportFragmentNavigator(supportFragmentManager, id.rootActivityMain) {
			override fun createFragment(screenKey: String?, data: Any?): BaseFragment =
				when (screenKey) {
					SCREEN_REPOS -> ReposFragment()
					else -> throw IllegalArgumentException("unknown fragment key")
				}

			override fun showSystemMessage(message: String?) {
				message?.let(::toast)
			}

			override fun exit() = finish()
		}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(layout.activity_main)
	}

	override fun onResume() {
		super.onResume()
		navigatorHolder.setNavigator(navigator)
	}

	override fun onPause() {
		super.onPause()
		navigatorHolder.removeNavigator()
	}


}


