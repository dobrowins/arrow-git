package com.dobrowins.arrowgit.views

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import arrow.core.toOption
import com.arellomobile.mvp.MvpAppCompatActivity
import com.dobrowins.arrowgit.R
import com.dobrowins.arrowgit.R.layout
import com.dobrowins.arrowgit.base.BaseFragment
import com.dobrowins.arrowgit.di.Scopes
import com.dobrowins.arrowgit.navigation.SCREEN_REPOS
import com.dobrowins.arrowgit.navigation.SCREEN_REPO_DETAIL
import com.dobrowins.arrowgit.navigation.SCREEN_START
import com.dobrowins.arrowgit.toast
import com.dobrowins.arrowgit.views.repodetail.RepoDetailFragment
import com.dobrowins.arrowgit.views.repos.ReposFragment
import com.dobrowins.arrowgit.views.start.StartFragment
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import ru.terrakok.cicerone.commands.Replace
import toothpick.Toothpick
import javax.inject.Inject

class MainActivity : MvpAppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: SupportFragmentNavigator =
        object : SupportFragmentNavigator(supportFragmentManager, R.id.rootActivityMain) {
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
                    else -> {
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
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

}
