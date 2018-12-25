package com.dobrowins.arrowktplayground.views.start

import arrow.syntax.function.forwardCompose
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.StartViewInteractor
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import com.dobrowins.arrowktplayground.views.mapThrowableMessage
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class StartViewPresenter @Inject constructor(
    private val interactor: StartViewInteractor,
    private val router: Router
) : BasePresenter<StartView>() {

    fun onEditTextDoneButtonClicked(profileName: String) =
        interactor.validateInput(profileName).fold(
            fe = mapThrowableMessage forwardCompose viewState::showSnackbar,
            fa = viewState::startReposFragment
        )

    val startReposFragment: (String) -> Unit = { profileName ->
        router.navigateTo(SCREEN_REPOS, profileName)
    }

}