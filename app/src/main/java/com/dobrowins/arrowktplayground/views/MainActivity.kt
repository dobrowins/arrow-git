package com.dobrowins.arrowktplayground.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import arrow.core.toOption
import com.arellomobile.mvp.MvpAppCompatActivity
import com.dobrowins.arrowktplayground.R
import com.dobrowins.arrowktplayground.R.id
import com.dobrowins.arrowktplayground.R.layout
import com.dobrowins.arrowktplayground.base.BaseFragment
import com.dobrowins.arrowktplayground.di.Scopes
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPO_DETAIL
import com.dobrowins.arrowktplayground.navigation.SCREEN_START
import com.dobrowins.arrowktplayground.toast
import com.dobrowins.arrowktplayground.views.repodetail.RepoDetailFragment
import com.dobrowins.arrowktplayground.views.repos.ReposFragment
import com.dobrowins.arrowktplayground.views.start.StartFragment
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Back
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity() {

	companion object {
		private const val DEFAULT_REPO = "dobrowins"
	}

	@Inject
	lateinit var navigatorHolder: NavigatorHolder

	private val navigator: SupportFragmentNavigator =
		object : SupportFragmentNavigator(supportFragmentManager, id.rootActivityMain) {
			override fun createFragment(screenKey: String?, data: Any?): BaseFragment =
				when (screenKey) {
					SCREEN_START -> StartFragment()
					SCREEN_REPOS -> ReposFragment.getInstance(data.toString())
					SCREEN_REPO_DETAIL -> RepoDetailFragment.getInstance(data.toString())
					else -> throw IllegalArgumentException("$screenKey is unknown fragment key!")
				}

			override fun showSystemMessage(message: String?) {
				message?.let(::toast)
			}

			override fun exit() = finish()

			override fun setupFragmentTransactionAnimation(
				command: Command?,
				currentFragment: Fragment?,
				nextFragment: Fragment?,
				fragmentTransaction: FragmentTransaction?
			) {
				when (command) {
					is Forward -> {
						fragmentTransaction?.setCustomAnimations(
							R.anim.enter_from_right,
							R.anim.exit_to_left,
							R.anim.enter_from_left,
							R.anim.exit_to_right
						)
					}
					is Replace -> {
					}
				}
			}
		}

	private val startAnew: () -> Unit = {
		val defaultCommand = Replace(SCREEN_START, null)
		navigator.applyCommands(arrayOf(defaultCommand))
	}

	private val restoreState: (Bundle) -> Unit = ::onRestoreInstanceState

	override fun onCreate(savedInstanceState: Bundle?) {
		inject()
		super.onCreate(savedInstanceState)
		setContentView(layout.activity_main)
		savedInstanceState.toOption().fold(startAnew, restoreState)
		navigatorHolder.setNavigator(navigator)
	}

	private fun inject() {
		Toothpick.openScopes(Scopes.APPLICATION, Scopes.ACTIVITY)
			.also { scope ->
				Toothpick.inject(this@MainActivity, scope)
				Toothpick.closeScope(scope.name)
			}
	}

	override fun onResume() {
		super.onResume()

	}

	override fun onPause() {
		super.onPause()
		navigatorHolder.removeNavigator()
	}

}
