package com.dobrowins.arrowktplayground.views.repos

import arrow.syntax.function.andThen
import com.arellomobile.mvp.InjectViewState
import com.dobrowins.arrowktplayground.base.BasePresenter
import com.dobrowins.arrowktplayground.domain.ReposViewInteractor
import com.dobrowins.arrowktplayground.domain.data.RepositoryData
import com.dobrowins.arrowktplayground.navigation.SCREEN_START
import com.dobrowins.arrowktplayground.views.mapThrowableMessage
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Artem Dobrovinskiy
 */
@InjectViewState
class ReposViewPresenter @Inject constructor(
    private val router: Router,
    private val reposViewInteractor: ReposViewInteractor
) : BasePresenter<ReposView>() {

    fun loadData(profileName: String): Unit {
		reposViewInteractor.fetchReposData(profileName).fold(
			ifLeft = mapThrowableMessage andThen viewState::showSnackbar,
			ifRight = mapToItems andThen viewState::showRepos
		)
	}

    fun onToolbarNavigationIconPressed() = router.navigateTo(SCREEN_START)

    private val mapToItems: (List<RepositoryData?>?) -> List<RepoItem> = { responseRepos ->
        responseRepos?.filterNotNull()?.map { data ->
            RepoItem(
                id = data.id,
                name = data.name,
                fullName = data.fullName,
                htmlUrl = data.htmlUrl,
                description = data.description
            )
        } ?: emptyList()
    }

}
