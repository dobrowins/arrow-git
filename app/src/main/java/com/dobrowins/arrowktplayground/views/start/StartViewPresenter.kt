package com.dobrowins.arrowktplayground.views.start

import arrow.core.Either
import arrow.core.flatMap
import arrow.instances.either.monad.flatMap
import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.R.id.rootStartFragment
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import com.dobrowins.arrowktplayground.views.mapThrowableMessage
import kotlinx.android.synthetic.main.fragment_start.rootStartFragment
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class StartViewPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<StartView>() {

    fun onEditTextDoneButtonClicked(profileName: String) {
        val isEmpty = when (profileName.isEmpty()) {
            true -> Either.Left(IllegalArgumentException("can't load repo without profile name!"))
            false -> Either.Right(profileName)
        }
        isEmpty.fold(
            ifLeft = mapThrowableMessage forwardCompose viewState::showSnackbar,
            ifRight = viewState::startReposFragment
        )
    }

    val startReposFragment: (String) -> Unit = { profileName ->
		router.navigateTo(SCREEN_REPOS, profileName)
	}

}