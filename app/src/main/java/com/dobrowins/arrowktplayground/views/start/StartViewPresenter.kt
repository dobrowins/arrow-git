package com.dobrowins.arrowktplayground.views.start

import arrow.core.Either
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.navigation.SCREEN_REPOS
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class StartViewPresenter @Inject constructor(
    private val router: Router
) : BasePresenter<StartView>() {

    fun onEditTextDoneButtonClicked(profileName: String): Either<Throwable, String> =
        when (profileName.isEmpty()) {
            true -> Either.Left(IllegalArgumentException("can't load repo without profile name!"))
            false -> Either.Right(profileName)
        }

    fun startReposFragment(profileName: String) = router.navigateTo(SCREEN_REPOS, profileName)

}